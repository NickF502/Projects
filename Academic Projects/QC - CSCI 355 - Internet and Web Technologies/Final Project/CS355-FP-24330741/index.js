// Require Statementss
const fs = require("fs");
const url = require("url");
const http = require("http");
const https = require("https");
const crypto = require("crypto");

// Load in API credentials for the two APIs
const pokemon_credentials = require("./credentials/pokemon_credentials.json");
const MAL_credentials = require("./credentials/MAL_credentials.json");

// Store multiple sessions
const all_sessions = [];

// Server Management
const port = 3000;
const host = `http://localhost:${port}`;
const server = http.createServer();
server.on("request", connection_handler);
server.on("listening", () => console.log(`Now Listening on Port ${port}`));
server.listen(port);



function connection_handler(req, res) {
    // Function to handle requests on ${host}

	console.log(`New Request for ${req.url} from ${req.socket.remoteAddress}`);

    // Home Page
    if (req.url === "/") {

        // Display index.html form for user to submit
        const main = fs.createReadStream("html/index.html");
        res.writeHead(200, {"Content-Type": "text/html"});
        main.pipe(res);
    }

    // When user hits "Find Card" button
    else if (req.url.startsWith("/find_card")) {

        // Extract the user's input - the name of the Pokemon
        const url = new URL(req.url, host);
        const name = url.searchParams.get("name");
        
        // If the user enters nothing in the text field, throw an Error 400
        if(name === ""){
            throwError(res, 400, `<h1>Error 400: Bad Request</h1>
                                  <p>Cannot leave field blank</p>`);
            return;
        }

        // Fix the capitalization - relevant for display purposes, not API
        const pokemonName = name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
    
        // Create a state and add a new session object to all_sessions
        const state = crypto.randomBytes(64).toString("hex");
        //console.log(`State: ${state}`);
        all_sessions.push({state, pokemonName});

        // Get a Pokemon card from the Pokemon TCG API
        request_pokemon_cards(pokemonName, state, res);
    }

    // On redirect from MAL after user has authorized their account
    else if (req.url.startsWith("/redirect")){

        // Access has been granted
        console.log("User has granted access to their MAL account");
        
        // Extract the authorization code and state from the url
        const {code, state} = url.parse(req.url, true).query;

        // Check to see if a cache exists and is still valid
        const cache_path = "./cache/token_cache.json";
        let cache_valid = false;

        // If the cache exists and the access_token has not expired, set cache_valid to TRUE
        if(fs.existsSync(cache_path)){
            cached_data = require(cache_path);
            if(new Date(cached_data.expiration > Date.now())){
                cache_valid = true;
            }
        }

        // If the access_token is valid, no need to get a new one - go straight to getting the top anime information
        if(cache_valid){
            console.log("Cache Valid!");
            mal_get_top_anime(cached_data.access_token, state, res);
        }

        // Otherwise, request a new access_token
        else{
            console.log("Requesting access token...");
            get_MAL_user_access_token(code, state, res);
        }

    }

    // Invalid Page reached
    else {
        throwError(res, 404, `<h1>Error 404: Page Not Found</h1>`);
    }
}



function throwError(res, error_code, error_msg){
    // Function to handle errors

    res.writeHead(error_code, {"Content-Type": "text/html"});
    res.end(error_msg);
}



function stream_to_message(stream, callback, ...args){
	// Function to convert a stream's chunks to a string message

	let body = "";
	stream.on("data", (chunk) => body += chunk);
	stream.on("end", () => callback(body, ...args));
}



function request_pokemon_cards(pokemonName, state, res){
    // Function to send a request to the Pokemon TCG API to retrieve Pokemon cards

    // Establish the message headers according to the APIs documentation
    const options = {
        method: "GET",
        headers: {
            "X-Api-Key": pokemon_credentials.API_Key    // API-Key Authentication
        }
    };

	// Establish the query information for the search
    const search_query_uri = `!name:${pokemonName}`;

    // Set up the search request to the endpoint
    const pokemon_search_endpoint = `https://api.pokemontcg.io/v2/cards?q=${search_query_uri}`;
	const pokemon_search_req = https.request(pokemon_search_endpoint, options,
        (pokemon_search_result_stream) => stream_to_message(pokemon_search_result_stream, pokemon_received_search_results, pokemonName, state, res)
    );

	// Send the search request to the Pokemon TCG API
	pokemon_search_req.end();
    console.log(`Request to the Pokemon TCG API for "${pokemonName}" cards has been made`);
}



function pokemon_received_search_results(serialized_search_object, pokemonName, state, res){
    // Function performed after the Pokemon TCG API's data has been processed
    console.log("Search Complete!");

    // Parse the result into a JSON object
    const search_results = JSON.parse(serialized_search_object);

    // If there are no results, that means that there is no Pokemon by the name of what the user entered. Throw an Error 404: Not Found
    if(search_results.totalCount === 0){
        console.log("0 results found");
        throwError(res, 404, `<h1>Error 404: Pokemon "${pokemonName}" Not Found</h1>`);
        return;
    }

    // Otherwise, retrieve the link to the image associated with the first result
    console.log(`${search_results.totalCount} result(s) found`);
    const image_link = search_results.data[0].images.small;

    // Get the active session and store the link in the session object
    const session = all_sessions.find((session) => session.state === state);
    session.image = image_link;

    // Redirect to MyAnimeList
    redirect_to_MAL(state, res);
}



function redirect_to_MAL(state, res){
    // Function performed synchronously, after the Pokemon TCG API has been interacted with
   
    // Set the authorization endpoint
	const mal_authorization_endpoint = "https://myanimelist.net/v1/oauth2/authorize";

    // Establish the url-encoded information of the message
	const uri = new URLSearchParams({
        "response_type": "code",
        "client_id": MAL_credentials.client_id,
        "code_challenge": state,
        "state":state
    }).toString();

    // Redirect the user to the MAL authorization endpoint
    res.writeHead(302, {Location: `${mal_authorization_endpoint}?${uri}`})
	.end();

    console.log("Authorization request to the MAL API has been made.");
    console.log("Waiting for the user to authorize access to MAL account...");
}



function get_MAL_user_access_token(code, state, res){
    // Function called when the user has given our app permission to act on its behalf (3LO)
    // We want to get a user access token so we can perform the actions we have permission to do

    // Set the authorization endpoint
	const mal_token_endpoint = "https://myanimelist.net/v1/oauth2/token";

    // Establish the url-encoded information of the message
	const uri = new URLSearchParams({
        "client_id": MAL_credentials.client_id,
        "client_secret": MAL_credentials.client_secret,
        "code_verifier": state,
        "code": code,
        "grant_type":"authorization_code",
    }).toString();

    // Establish the message headers according to the APIs documentation
	const options = {
		method: "POST",
		headers:{
			"Content-Type":"application/x-www-form-urlencoded"
		}
	};

    // Create a date for caching
    const cache_date = new Date();

    // Send the request to retrieve the user access token and cache it when obtained
	https.request(mal_token_endpoint, options, (token_stream) => stream_to_message(token_stream, mal_received_token, cache_date, state, res))
    .end(uri);
}



function mal_received_token(serialized_mal_user_token_object, cache_date, state, res){
    // Function to cache the user tokens
    console.log("Token Acquired!");

    // Parse the result into a JSON object
    const mal_user_token_object = JSON.parse(serialized_mal_user_token_object);
    
    // Cache the token
    mal_user_token_object.expiration = new Date(cache_date.getTime() + mal_user_token_object.expires_in);
    fs.writeFile("./cache/token_cache.json", JSON.stringify(mal_user_token_object), () => console.log("Token Cached!") );

    // Extract the access token
    const mal_access_token = mal_user_token_object.access_token;

    // Get the top rated anime
    mal_get_top_anime(mal_access_token, state, res);
}



function mal_get_top_anime(mal_access_token, state, res){
    // Function to retrieve the top 10 anime
    
    // Use the access_token to get the top 10 anime
    const mal_request_endpoint = "https://api.myanimelist.net/v2";
    const mal_top_ten_uri = "/anime/ranking?ranking_type=all&limit=10";
    const mal_mal_top_ten_endpoint = `${mal_request_endpoint}${mal_top_ten_uri}`;

    // Establish the message headers according to the APIs documentation
    const options = {
        method: "GET",
        headers:{
            "Authorization":`Bearer ${mal_access_token}`
        }
    };

    // Send the request to get the top anime
    https.request(mal_mal_top_ten_endpoint, options, (mal_top_ten_stream) => stream_to_message(mal_top_ten_stream, mal_recevied_top_info, state, res))
    .end();
}



function mal_recevied_top_info(serialized_mal_top_ten_object, state, res){
    // Function called after MAL responds with the top 10 anime - extract the data and store it in the session object
    console.log("Information Acquired!");

    // Parse the result into a JSON object
    const mal_object = JSON.parse(serialized_mal_top_ten_object);

    // Extract the top 10 anime and store them in an array
    const top_10_anime = [];
    for(let i=0; i<10; i++){
        top_10_anime.push(mal_object.data[i].node.title);
    }

    // Get the active session and store the data to the session object
    const session = all_sessions.find((session) => session.state === state);
    session.top_ten = top_10_anime;

    // Display the results
    display_results(session, res);
}



function display_results(session, res){
    // Display the results for that session

    let ranks = session.top_ten;

    // Display basic HTML with information gathered across the session
    res.end(`<h1>Results</h1>
        <h2>Here's a picture of one of ${session.pokemonName}'s TCG cards</h2>
        <img src=${session.image} alt="Image of ${session.pokemonName}'s TCG card">
        <h2>Top 10 Current Anime according to MyAnimeList:</h2>
        <ol>
            <li>${ranks[0]}</li>
            <li>${ranks[1]}</li>
            <li>${ranks[2]}</li>
            <li>${ranks[3]}</li>
            <li>${ranks[4]}</li>
            <li>${ranks[5]}</li>
            <li>${ranks[6]}</li>
            <li>${ranks[7]}</li>
            <li>${ranks[8]}</li>
            <li>${ranks[9]}</li>
        </ol>`);

    console.log("Results have been displayed");
}


