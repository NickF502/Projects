import time
import turtle
import random
import player_rosters

# Game Information
home_team = "NYM"
away_team = "SEA"
home_name_full = "New York Mets"
away_name_full = "Seattle Mariners"

# Additional Starting Info
home_inning_runs_counter, home_total_runs_counter, home_hits_counter, home_lob_counter, away_inning_runs_counter, away_total_runs_counter, away_hits_counter, away_lob_counter = 0, 0, 0, 0, 0, 0, 0, 0
home_batter_pos, away_batter_pos = 0, 0
outs_counter, runs_counter = 0, 0
inning = 1
runners = [0, 0, 0]


game_speed = float(input("Enter a game speed. (0 is fastest): "))


mets_pitchers = ["Jacob deGrom", "Adam Ottavino", "Edwin Diaz"]
mariners_pitchers = ["Luis Castillo", "Erik Swanson", "Paul Sewald"]

field_out_type = ["grounds out", "lines out", "flies out"]
field_positions = ["left", "center", "right"]


# Functions
def set_display():
    # Home
    make_border(630, -60, 13, 0.1)
    make_border(630, 350, 13, 0.1)
    make_border(630, 310, 13, 0.1)
    make_border(630, 35, 13, 0.1)
    make_border(500, 145, 0.1, 20.5)

    # Away
    make_border(-640, -60, 13, 0.1)
    make_border(-640, 350, 13, 0.1)
    make_border(-640, 310, 13, 0.1)
    make_border(-640, 35, 13, 0.1)
    make_border(-510, 145, 0.1, 20.5)

    # Scoreboard
    make_border(0, 350, 43, 0.1)
    make_border(0, 305, 43, 0.1)
    make_border(0, 260, 43, 0.1)
    make_border(-430, 305, 0.1, 4.5)
    for i in range(0, 13):
        make_border(i*50 - 170, 305, 0.1, 4.5)


    home_name_marker = turtle.Turtle()
    home_name_marker.hideturtle()
    home_name_marker.penup()
    home_name_marker.goto(-300, 270)
    home_name_marker.clear()
    home_name_marker.color("#002D72")
    home_name_marker.write("{}".format(home_name_full), align="center", font=("Courier", 18, "bold"))

    away_name_marker = turtle.Turtle()
    away_name_marker.hideturtle()
    away_name_marker.penup()
    away_name_marker.goto(-300, 315)
    away_name_marker.clear()
    away_name_marker.color("#005C5C")
    away_name_marker.write("{}".format(away_name_full), align="center", font=("Courier", 18, "bold"))

    # Runner Window
    runner_window = turtle.Turtle()
    runner_window.hideturtle()
    runner_window.color("black")
    runner_window.width(4)
    runner_window.penup()
    runner_window.goto(0, 200)
    runner_window.pendown()
    runner_window.left(-45)
    runner_window.forward(300)
    runner_window.left(-90)
    runner_window.forward(150)
    runner_window.left(-90)
    runner_window.forward(300)
    runner_window.left(90)
    runner_window.forward(150)
    runner_window.left(90)
    runner_window.forward(150)
    runner_window.left(90)
    runner_window.forward(300)
    runner_window.left(90)
    runner_window.forward(150)
    runner_window.left(90)
    runner_window.forward(150)


loading = turtle.Turtle()
loading.hideturtle()
loading.penup()
loading.goto(0, -250)
loading.clear()
loading.color("gray")
loading.write("Loading...", align="center", font=("Courier", 50, "normal"))


runner_on_first_square = turtle.Turtle()
runner_on_first_square.hideturtle()
runner_on_first_square.penup()
runner_on_first_square.goto(106, -166)
runner_on_first_square.clear()
runner_on_first_square.color("gray")
runner_on_first_square.write("◆", align="center", font=("Courier", 183, "normal"))

time.sleep(0.5)

runner_on_second_square = turtle.Turtle()
runner_on_second_square.hideturtle()
runner_on_second_square.penup()
runner_on_second_square.goto(0, -60)
runner_on_second_square.clear()
runner_on_second_square.color("gray")
runner_on_second_square.write("◆", align="center", font=("Courier", 183, "normal"))

time.sleep(0.5)

runner_on_third_square = turtle.Turtle()
runner_on_third_square.hideturtle()
runner_on_third_square.penup()
runner_on_third_square.goto(-106, -166)
runner_on_third_square.clear()
runner_on_third_square.color("gray")
runner_on_third_square.write("◆", align="center", font=("Courier", 183, "normal"))

time.sleep(0.5)


def color_bases(a, marker1, marker2, marker3):
    if a[0] == 1:
        marker1.color("gold")
    else:
        marker1.color("gray")
    marker1.clear()
    marker1.write("◆", align="center", font=("Courier", 183, "normal"))

    if a[1] == 1:
        marker2.color("gold")
    else:
        marker2.color("gray")
    marker2.clear()
    marker2.write("◆", align="center", font=("Courier", 183, "normal"))

    if a[2] == 1:
        marker3.color("gold")
    else:
        marker3.color("gray")
    marker3.clear()
    marker3.write("◆", align="center", font=("Courier", 183, "normal"))


def walk(a, rc):
    if a[0] == 0:
        a[0] = 1
        return a, rc
    else:
        if a[1] == 0:
            a[1] = 1
            return a, rc
        else:
            if a[2] == 0:
                a[2] = 1
                return a, rc
            else:
                rc += 1
                return a, rc


def one_base(a, rc):
    if a == [0, 0, 0]:
        a = [1, 0, 0]

    elif a == [1, 0, 0]:
        a = [1, 0, 1]

    elif a == [0, 1, 0]:
        a = [1, 0, 0]
        rc += 1

    elif a == [0, 0, 1]:
        a = [1, 0, 0]
        rc += 1

    elif a == [1, 1, 0]:
        a = [1, 0, 1]
        rc += 1

    elif a == [1, 0, 1]:
        a = [1, 0, 0]
        rc += 1

    elif a == [0, 1, 1]:
        a = [1, 0, 0]
        rc += 2

    elif a == [1, 1, 1]:
        a = [1, 0, 1]
        rc += 2

    else:
        exit("Runner Config Not Established")
    return a, rc


def two_base(a, rc):
    rc += sum(a)
    a = [0, 1, 0]
    return a, rc


def three_base(a, rc):
    rc += sum(a)
    a = [0, 0, 1]
    return a, rc


def four_base(a, rc):
    rc += (sum(a) + 1)
    a = [0, 0, 0]
    return a, rc


def next_inning_box(xcor):
    new_x = xcor + 50
    return new_x


# FIXME add to pitchers
def next_player(y):
    if y == 35:
        new_y = 275
    else:
        new_y = y - 30
    return new_y


def calc_odds(p, b):
    odds = ((p+b)/2.0) * 1000
    rand = random.randint(1, 1000)
    if rand <= odds:
        return "Y"
    else:
        return "N"


def calc_tot_bases(p, b, a, rc):
    tot_bases = round(abs(p+b) / 2.0)
    rand = random.randint(1, 500)
    if rand*tot_bases < 650:
        a1, rc1 = one_base(a, rc)
        return "single", a1, rc1
    elif 650 <= rand*tot_bases < 875:
        a1, rc1 = two_base(a, rc)
        two_base(a, rc)
        return "double", a1, rc1
    elif 875 <= rand*tot_bases < 900:
        a1, rc1 = three_base(a, rc)
        three_base(a, rc)
        return "triple", a1, rc1
    elif rand*tot_bases >= 900:
        a1, rc1 = four_base(a, rc)
        return "homer", a1, rc1
    else:
        return "ERROR ERROR ERROR"


def make_border(xcor, ycor, strchlen, strchwid):
    border = turtle.Turtle()
    border.shape("square")
    border.color("black")
    border.speed(0)
    border.penup()
    border.goto(xcor, ycor)
    border.shapesize(stretch_len=strchlen, stretch_wid=strchwid)
    return border

# More Display Stuff


# Create Base Screen
wn = turtle.Screen()
wn.title("Baseball Simulator by NickF502")
wn.bgcolor("gray")
wn.setup(width=1.0, height=1.0)
wn.tracer(0)
set_display()


# Create Home Display
penHome = turtle.Turtle()
penHome.hideturtle()
penHome.penup()
penHome.clear()
penHome.goto(635, 310)
penHome.color("#002D72")
penHome.write(home_team, align="center", font=("Courier", 24, "bold"))
penHome.color("black")


for Player in player_rosters.mets:
    home_y = penHome.ycor()
    penHome.sety(home_y - 30.0)
    penHome.setx(540)
    penHome.write(Player.number, align="left", font=("Courier", 14, "normal"))
    penHome.setx(580)
    penHome.write(Player.name, align="left", font=("Courier", 14, "normal"))

# Home Batter Indicator
home_batter_marker = turtle.Turtle()
home_batter_marker.hideturtle()
home_batter_marker.penup()
home_batter_marker.goto(520, 275)
home_batter_marker.clear()
home_batter_marker.color("#002D72")
home_batter_marker.write("•", align="center", font=("Courier", 20, "normal"))

# Home Pitcher Indicator
home_pitcher_marker = turtle.Turtle()
home_pitcher_marker.hideturtle()
home_pitcher_marker.penup()
home_pitcher_marker.goto(520, 5)
home_pitcher_marker.clear()
home_pitcher_marker.color("#002D72")

home_pitcher_marker.write("•", align="center", font=("Courier", 20, "normal"))

# Create Away Display
penAway = turtle.Turtle()
penAway.hideturtle()
penAway.penup()
penAway.clear()
penAway.goto(-650, 310)
penAway.color("#005C5C")
penAway.write(away_team, align="center", font=("Courier", 24, "bold"))
penAway.color("black")

for Player in player_rosters.mariners:
    away_y = penAway.ycor()
    penAway.sety(away_y - 30.0)
    penAway.setx(-735)
    penAway.write(Player.number, align="left", font=("Courier", 14, "normal"))
    penAway.setx(-695)
    penAway.write(Player.name, align="left", font=("Courier", 14, "normal"))

# Away Batter Indicator
away_batter_marker = turtle.Turtle()
away_batter_marker.hideturtle()
away_batter_marker.penup()
away_batter_marker.goto(-755, 275)
away_batter_marker.clear()
away_batter_marker.color("#005C5C")
away_batter_marker.write("•", align="center", font=("Courier", 20, "normal"))

# Away Pitcher Indicator
away_pitcher_marker = turtle.Turtle()
away_pitcher_marker.hideturtle()
away_pitcher_marker.penup()
away_pitcher_marker.goto(-755, 5)
away_pitcher_marker.clear()
away_pitcher_marker.color("#005C5C")

away_pitcher_marker.write("•", align="center", font=("Courier", 20, "normal"))


temp_home_inning_runs_marker = turtle.Turtle()
temp_home_inning_runs_marker.hideturtle()
temp_home_inning_runs_marker.penup()
temp_home_inning_runs_marker.goto(-145, 265)
temp_home_inning_runs_marker.clear()
temp_home_inning_runs_marker.color("white")

home_inning_runs_marker = turtle.Turtle()
home_inning_runs_marker.hideturtle()
home_inning_runs_marker.penup()
home_inning_runs_marker.goto(-145, 265)
home_inning_runs_marker.clear()
home_inning_runs_marker.color("white")

home_total_runs_marker = turtle.Turtle()
home_total_runs_marker.hideturtle()
home_total_runs_marker.penup()
home_total_runs_marker.goto(305, 265)
home_total_runs_marker.clear()
home_total_runs_marker.color("white")
home_total_runs_marker.write("{}".format(home_inning_runs_counter), align="center", font=("Courier", 20, "normal"))

home_hits_marker = turtle.Turtle()
home_hits_marker.hideturtle()
home_hits_marker.penup()
home_hits_marker.goto(355, 265)
home_hits_marker.clear()
home_hits_marker.color("white")
home_hits_marker.write("{}".format(home_hits_counter), align="center", font=("Courier", 20, "normal"))

home_lob_marker = turtle.Turtle()
home_lob_marker.hideturtle()
home_lob_marker.penup()
home_lob_marker.goto(405, 265)
home_lob_marker.clear()
home_lob_marker.color("white")
home_lob_marker.write("{}".format(home_inning_runs_counter), align="center", font=("Courier", 20, "normal"))

temp_away_inning_runs_marker = turtle.Turtle()
temp_away_inning_runs_marker.hideturtle()
temp_away_inning_runs_marker.penup()
temp_away_inning_runs_marker.goto(-145, 310)
temp_away_inning_runs_marker.clear()
temp_away_inning_runs_marker.color("white")

away_inning_runs_marker = turtle.Turtle()
away_inning_runs_marker.hideturtle()
away_inning_runs_marker.penup()
away_inning_runs_marker.goto(-145, 310)
away_inning_runs_marker.clear()
away_inning_runs_marker.color("white")

away_total_runs_marker = turtle.Turtle()
away_total_runs_marker.hideturtle()
away_total_runs_marker.penup()
away_total_runs_marker.goto(305, 310)
away_total_runs_marker.clear()
away_total_runs_marker.color("white")
away_total_runs_marker.write("{}".format(away_inning_runs_counter), align="center", font=("Courier", 20, "normal"))

away_hits_marker = turtle.Turtle()
away_hits_marker.hideturtle()
away_hits_marker.penup()
away_hits_marker.goto(355, 310)
away_hits_marker.clear()
away_hits_marker.color("white")
away_hits_marker.write("{}".format(away_hits_counter), align="center", font=("Courier", 20, "normal"))

away_lob_marker = turtle.Turtle()
away_lob_marker.hideturtle()
away_lob_marker.penup()
away_lob_marker.goto(405, 310)
away_lob_marker.clear()
away_lob_marker.color("white")
away_lob_marker.write("{}".format(home_inning_runs_counter), align="center", font=("Courier", 20, "normal"))

outs_counter_marker = turtle.Turtle()
outs_counter_marker.hideturtle()
outs_counter_marker.penup()
outs_counter_marker.goto(100, 50)
outs_counter_marker.clear()
outs_counter_marker.color("red")
outs_counter_marker.write("•" * outs_counter, align="left", font=("Courier", 150, "normal"))

inning_marker = turtle.Turtle()
inning_marker.hideturtle()
inning_marker.penup()
inning_marker.goto(-275, 75)
inning_marker.color("gold")
inning_marker.clear()
inning_marker.write("{}".format(inning), align="center", font=("Courier", 75, "bold"))

inning_tb_marker = turtle.Turtle()
inning_tb_marker.hideturtle()
inning_tb_marker.penup()
inning_tb_marker.color("gold")
inning_tb_marker.goto(-275, 125)
inning_tb_marker.clear()
inning_tb_marker.write("{}".format("-"), align="center", font=("Courier", 75, "bold"))

# Play Ball!
transcript = turtle.Turtle()
transcript.hideturtle()
transcript.penup()
transcript.goto(0, 370)
transcript.clear()
transcript.write("Play Ball!", align="center", font=("Courier", 14, "normal"))
wn.update()

time.sleep(game_speed)


# Full Game Loop
while True:
    wn.update()
    transcript.clear()

    if inning == 19:
        if home_total_runs_counter > away_total_runs_counter:
            transcript.clear()
            transcript.write("That's the game! The {} walk it off {}-{}!".format(home_name_full, home_total_runs_counter, away_total_runs_counter), align="center", font=("Courier", 14, "normal"))
            time.sleep(60)
            exit("Game Over!")


    # Set up new inning if need be

    if outs_counter == 3:
        inning += 1
        inning_marker.clear()
        inning_marker.write("{}".format((inning + 1) // 2), align="right", font=("Courier", 75, "bold"))

        outs_counter = 0
        runners = [0, 0, 0]

        inning_marker.clear()
        inning_marker.write("{}".format((inning + 1) // 2), align="center", font=("Courier", 75, "bold"))

        inning_tb_marker.clear()

        if inning % 2 == 0:
            temp_away_inning_runs_marker.clear()
            temp_away_inning_runs_marker.setx(next_inning_box(temp_away_inning_runs_marker.xcor()))
            away_inning_runs_marker.write("{}".format(away_inning_runs_counter), align="center", font=("Courier", 20, "normal"))
            away_inning_runs_marker.setx(next_inning_box(away_inning_runs_marker.xcor()))
            inning_tb_marker.sety(25)
            inning_tb_marker.write("{}".format("-"), align="center", font=("Courier", 75, "bold"))
        else:
            temp_home_inning_runs_marker.clear()
            temp_home_inning_runs_marker.setx(next_inning_box(temp_home_inning_runs_marker.xcor()))
            home_inning_runs_marker.write("{}".format(home_inning_runs_counter), align="center", font=("Courier", 20, "normal"))
            home_inning_runs_marker.setx(next_inning_box(home_inning_runs_marker.xcor()))
            inning_tb_marker.sety(125)
            inning_tb_marker.write("{}".format("-"), align="center", font=("Courier", 75, "bold"))

            away_inning_runs_counter = home_inning_runs_counter = runs_counter = 0



        # Decide the Winner if Game is Over
        if inning == 18:
            if home_total_runs_counter > away_total_runs_counter:
                transcript.write("That's the game! The {} win {}-{}!".format(home_name_full, home_total_runs_counter, away_total_runs_counter), align="center", font=("Courier", 14, "normal"))
                home_inning_runs_marker.write("{}".format("X"), align="center", font=("Courier", 20, "normal"))
                time.sleep(60)
                exit("Game Over!")
            else:
                pass
        elif inning == 19:
            if home_total_runs_counter > away_total_runs_counter:
                transcript.write("That's the game! The {} win {}-{}!".format(home_name_full, home_total_runs_counter, away_total_runs_counter), align="center", font=("Courier", 14, "normal"))
                time.sleep(60)
                exit("Game Over!")
            elif away_total_runs_counter > home_total_runs_counter:
                transcript.write("That's the game! The {} win {}-{}!".format(away_name_full, away_total_runs_counter, home_total_runs_counter), align="center", font=("Courier", 14, "normal"))
                time.sleep(60)
                exit("Game Over!")
            elif away_total_runs_counter == home_total_runs_counter:
                transcript.write("That's the game! It's a tie!", align="center", font=("Courier", 14, "normal"))
                time.sleep(60)
                exit("Game Over!")
        else:
            transcript.clear()
            transcript.write("Changing Sides...", align="center", font=("Courier", 14, "normal"))
            time.sleep(game_speed)

        color_bases(runners, runner_on_first_square, runner_on_second_square, runner_on_third_square)
        outs_counter_marker.clear()

    # Determine the Pitcher for the Inning
    if inning < 15:
        if inning % 2 != 0:
            current_pitcher = player_rosters.mets_pitchers[0]
        else:
            current_pitcher = player_rosters.mariners_pitchers[0]
    elif inning == 15:
        current_pitcher = player_rosters.mets_pitchers[1]
    elif inning == 16:
        current_pitcher = player_rosters.mariners_pitchers[1]
    elif inning == 17:
        current_pitcher = player_rosters.mets_pitchers[2]
    elif inning == 18:
        current_pitcher = player_rosters.mariners_pitchers[2]
    else:
        current_pitcher = "Joe Random"


    # Determine the Batter
    if inning % 2 != 0:
        current_batter = player_rosters.mariners_batters[away_batter_pos]
    else:
        current_batter = player_rosters.mets_batters[home_batter_pos]

    transcript.clear()
    transcript.write("{} pitches to {}...".format(current_pitcher.name, current_batter.name), align="center", font=("Courier", 14, "normal"))
    time.sleep(game_speed)
    transcript.clear()




    # Calculate PA Result

    pa_result = ""
    # Does batter K?
    # If yes...
    if calc_odds(current_pitcher.kratea, current_batter.krate) == "Y":
        transcript.write("{} strikes out".format(current_batter.name), align="center", font=("Courier", 14, "normal"))
        outs_counter += 1
    # If no, does batter walk?
    else:
        if calc_odds(current_pitcher.bbratea, current_batter.bbrate) == "Y":
            runners, runs_counter = walk(runners, runs_counter)
            transcript.write("{} walks".format(current_batter.name), align="center", font=("Courier", 14, "normal"))
        # If no, does batter get a hit?
        else:
            if calc_odds(current_pitcher.babipa, current_batter.babip) == "N":
                transcript.write("{} {}".format(current_batter.name, field_out_type[random.randint(0, 2)]), align="center", font=("Courier", 14, "normal"))
                outs_counter += 1
            # If yes, how many bases?
            else:
                pa_result, runners, runs_counter = calc_tot_bases(current_pitcher.slga, current_batter.slg, runners, runs_counter)
                transcript.write("{} hits a {} to {}".format(current_batter.name, pa_result, field_positions[random.randint(0, 2)]), align="center", font=("Courier", 14, "normal"))
                if inning % 2 != 0:
                    away_hits_counter += 1
                    away_hits_marker.clear()
                    away_hits_marker.write("{}".format(away_hits_counter), align="center", font=("Courier", 20, "normal"))

                else:
                    home_hits_counter += 1
                    home_hits_marker.clear()
                    home_hits_marker.write("{}".format(home_hits_counter), align="center", font=("Courier", 20, "normal"))

    color_bases(runners, runner_on_first_square, runner_on_second_square, runner_on_third_square)

    outs_counter_marker.clear()
    outs_counter_marker.write("•"*outs_counter, align="left", font=("Courier", 150, "normal"))





    # If runs scores, add runs to scoreboard in 2 places


    if runs_counter != 0:
        if inning % 2 != 0:
            away_inning_runs_counter += runs_counter
            away_total_runs_counter += runs_counter
            temp_away_inning_runs_marker.clear()
            temp_away_inning_runs_marker.write("{}".format(away_inning_runs_counter), align="center", font=("Courier", 20, "normal"))
            away_total_runs_marker.clear()
            away_total_runs_marker.write("{}".format(away_total_runs_counter), align="center", font=("Courier", 20, "normal"))

        else:
            home_inning_runs_counter += runs_counter
            home_total_runs_counter += runs_counter
            temp_home_inning_runs_marker.clear()
            temp_home_inning_runs_marker.write("{}".format(home_inning_runs_counter), align="center", font=("Courier", 20, "normal"))
            home_total_runs_marker.clear()
            home_total_runs_marker.write("{}".format(home_total_runs_counter), align="center", font=("Courier", 20, "normal"))

    runs_counter = 0



    time.sleep(game_speed)
    transcript.clear()





    # Advance to Next Batter

    if inning % 2 != 0:
        away_batter_marker.sety(next_player(away_batter_marker.ycor()))
        away_batter_marker.clear()
        away_batter_marker.write("•", align="center", font=("Courier", 20, "normal"))
        if away_batter_pos == 8:
            away_batter_pos = 0
        else:
            away_batter_pos += 1

    else:
        home_batter_marker.sety(next_player(home_batter_marker.ycor()))
        home_batter_marker.clear()
        home_batter_marker.write("•", align="center", font=("Courier", 20, "normal"))
        if home_batter_pos == 8:
            home_batter_pos = 0
        else:
            home_batter_pos += 1
