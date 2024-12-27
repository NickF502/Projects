import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn
from sklearn.datasets import load_breast_cancer
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import train_test_split

def eda(dataset):
    """
    Function to perform exploratory data analysis (EDA) on a dataset, as well as turn it into a dataframe.
    The EDA performed will check for null values, print out some information on the dataset's features and some stats
    on each feature's data, as well create a histogram of the features and calculate the correlation matrix, creating a
    heatmap out of it
    :param dataset: A dataset to perform exploratory data analysis on
    :return: A dataframe containing the dataset's contents
    """

    # Extract the features and data from the dataset
    features = dataset.feature_names
    data = dataset.data

    # Create a pandas dataframe to easily display the contents
    dataframe = pd.DataFrame(data, columns=features)

    # Check for null values
    print("Null Values:")
    print(dataframe.isnull().sum())

    # Extract information about the dataset's features
    print("\n\n\nBasic Information:")
    print(dataframe.info())

    # Extract statistics for each feature
    print("\n\n\n Description Statistics:")
    print(dataframe.describe())

    # Plot the dataset as a set of histograms for each feature and save the file
    dataframe.hist(bins=20, figsize=(20, 15))
    plt.title('Feature Histogram')
    plt.savefig('Feature Histogram.pdf')
    plt.close()

    # Calculate the correlation matrix and plot it as a heatmap
    plt.figure(figsize=(20, 15))
    plt.title("Correlation Graph")
    seaborn.heatmap(dataframe.corr(), cmap='coolwarm', annot=True, fmt=".2f", square=True)
    plt.savefig("Correlation Graph.pdf")
    plt.close()

    # Return the adjust dataframe
    return dataframe



def data_transformation(dataframe):
    """
    Function that takes a dataframe and applies a MinMaxScaler to each feature, which normalizes the values to range
    between 0 and 1
    :param dataframe: the dataframe to apply the scaling to
    :return: the scaled dataframe
    """

    # Scale the data using min-max scaling, meaning all datapoints will fall on the interval [0, 1]
    scaler = MinMaxScaler()
    scaled_d = scaler.fit_transform(dataframe)

    # Turned the scaled data into a new dataframe
    s = pd.DataFrame(scaled_d, columns=dataframe.columns)

    # Return the scaled dataframe
    return s



def initialize_w(df, target):
    """
    Function to initialize the weights for the training model using the pseudo-inverse
    :param df: The dataframe of the dataset
    :param target: The target of the dataset
    :return: The initial weights
    """

    # Add an x_bias column of ones for weight calculations
    x_bias = np.hstack((np.ones((df.shape[0], 1)), df))

    # Calculate the pseudo-inverse of x_bias
    x_pseudo_inverse = np.linalg.pinv(x_bias)

    # Calculate the weights w
    w = np.dot(x_pseudo_inverse, target)

    return w



def pocket_alg(df, y, w):
    """
    Function that trains the weights to improve the accuracy of the classifier
    :param df: dataframe
    :param y: dataframe's target
    :param w: initial weights
    :return: final weights
    """

    # Initialize best_w to an array of 0s, best_err to +inf
    best_w = np.zeros(df.shape[1])
    best_err = float('inf')

    # Set the number of iterations
    num_iterations = 5000

    # Loop num_iterations times...
    for iteration in range(num_iterations):

        # Make predictions - numpy array of True and False (true if entry is >= 0 (1))
        pred = np.dot(df, w) >= 0

        # Get the indices of wrong predictions (if any)
        wrong_pred_indices = np.where(pred != y)[0]

        # If wrong predictions were made
        if wrong_pred_indices.size != 0:

            # Get the wrong data and the labels
            wrong_data = df[wrong_pred_indices]
            wrong_labels = y[wrong_pred_indices]

            # Update the weights for the wrong ones
            # Calculate the difference (1, -1), reshape to column vector, and multiply
            updated_wrong_weights = (wrong_labels - pred[wrong_pred_indices])[:, np.newaxis] * wrong_data
            w += updated_wrong_weights.sum(axis=0)

        # Calculate the error with the current weights
        err = np.mean(pred != y)

        # Update best_w and best_err
        if err < best_err:
            best_err, best_w = err, w.copy()

    # Return the final weights and the error
    return best_w, best_err



def make_error_plot(results, transformed):
    """
    Function to plot e_in and e_out vs n
    :param results: list that stores trial number, in sample error, out sample error, and number of training data
    points in a tuple
    :param transformed: boolean representing whether data transformation was applied or not
    :return:
    """

    # Unpack the results into individual lists
    e_ins = []
    e_outs = []
    ns = []

    for i, ein, eout, n in results:
        e_ins.append(ein)
        e_outs.append(eout)
        ns.append(n)

    # Create the plot figure
    plt.figure(figsize=(10, 6))

    # Plot E_in
    plt.plot(ns, e_ins, label='E_in', marker='o', color='orange')

    # Plot E_out
    plt.plot(ns, e_outs, label='E_out', marker='x', color='blue')

    # Set the plot's axis labels
    plt.xlabel('Number of Training Data Points - N')
    plt.ylabel('Errors - E_in and E_out')

    # Handle the attributes that change depending on whether the data is transformed or not
    if transformed:
        path = "Transformed Error Plot.pdf"
        title = 'E_in and E_out vs. N (w/ Data Transformation)'
    else:
        path = "Original Error Plot.pdf"
        title = 'E_in and E_out vs. N (No Data Transformation)'

    # Add the plot's title, legend, and grid, then save and close the plot
    plt.title(title)
    plt.legend()
    plt.grid(True)
    plt.savefig(path)
    plt.close()



def main(do_dt):

    # Load the iris dataset
    breast_cancer = load_breast_cancer()

    # Initialize the target of the dataset
    target = breast_cancer.target

    # Perform Exploratory Data Analysis
    dataframe = eda(breast_cancer)

    # Perform Necessary Data Transformation (if do_dT is True)
    scaled_dataframe = data_transformation(dataframe) if do_dt else dataframe

    # Initialize a list to store the results of the 10 trials
    results = []

    # Initialize the training-test split
    split = 0.05

    # Train and validate with 10 sample sets
    for i in range(10):

        # Split the data set into training and testing parts
        x_train, x_test, y_train, y_test = train_test_split(scaled_dataframe, target, train_size=split)

        # Initialize the weights using linear regression and pseudo-inverse
        initial_weights = initialize_w(x_train, y_train)

        # Train the weights using the pocket algorithm and get the in-sample error
        x_bias_training = np.hstack((np.ones((x_train.shape[0], 1)), x_train))
        weights, in_error = pocket_alg(x_bias_training, y_train, initial_weights)

        # Validate the model using the testing data and get the out-sample error
        x_bias_testing = np.hstack((np.ones((x_test.shape[0], 1)), x_test))
        test_predictions = np.dot(x_bias_testing, weights) >= 0
        out_error = np.mean(test_predictions != y_test)

        # Append the results to the list
        results.append((i, in_error, out_error, x_train.shape[0]))

        # Increase the split by 0.1
        split += 0.1

    # Plot the results
    make_error_plot(results, do_dt)



if __name__ == "__main__":

    # Display all columns of the dataframes when printing
    pd.set_option("display.max_column", None)

    # Run calculations twice - one time while normalizing the data and one time while not normalizing it
    main(True)
    main(False)
