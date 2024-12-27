import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.datasets import load_breast_cancer
from sklearn.preprocessing import MinMaxScaler, PolynomialFeatures
from sklearn.model_selection import KFold, cross_val_score
from sklearn.linear_model import LogisticRegression



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



def plot_results(results, lambda_values):
    """
    Function to plot the results of the trial
    :param results: The E_val values for each polynomial degree - y axis
    :param lambda_values: The values of lambda used - x axis
    """

    # Set up the plot
    plt.xscale('log')   # Use a log scale because lambda values grow exponentially
    plt.xlabel('Regularization (λ)')
    plt.ylabel('Average Validation Error (E_Val)')
    plt.title('E_Val vs λ for Various Polynomial Degrees')

    # Plot results for each polynomial degree
    for degree, error in results.items():
        plt.plot(lambda_values, error, label="Φ{}(x)".format(degree))

    plt.legend()
    plt.savefig('E_val vs Regularization Plot.pdf')
    plt.show()



def main():

    # Load the breast_cancer dataset
    dataset = load_breast_cancer()

    # Initialize the data, target, and features of the dataset
    data = dataset.data
    target = dataset.target
    features = dataset.feature_names

    # Create a pandas dataframe
    df = pd.DataFrame(data, columns=features)

    # Perform Data Transformation
    df = data_transformation(df)

    # Initialize an array of lambda values
    lambda_values = [0.00001, 0.0001, 0.001, 0.01, 0.1, 1, 10, 100, 1000, 10000]

    # Create a dictionary to store results for each polynomial degree
    results = {}

    # For each polynomial degree n...
    for n in [1, 2, 3]:

        print("\n\nResults for Dimension {}".format(n))

        # Add an entry in the results dictionary for the n-th polynomial degree
        results[n] = []

        # Transform the feature space to the n-th dimension
        poly = PolynomialFeatures(n)
        x_poly = poly.fit_transform(df)

        # Initialize the 5-fold cross validation
        five_fold_validation = KFold(n_splits=5, shuffle=True)

        # Calculate average error for each lambda value
        for lmbd in lambda_values:

            # Initialize the logistic regression model with lambda regularization
            logistic_regression_model = LogisticRegression(C=lmbd)

            # Apply 5-fold cross validation to get the accuracy scores
            eval_scores = cross_val_score(logistic_regression_model, x_poly, target, cv=five_fold_validation, scoring='accuracy')

            # Convert the accuracy scores to error scores (error = 1 - accuracy) and get the average for that lmbd value
            for i in range(len(eval_scores)):
                eval_scores[i] = 1 - eval_scores[i]
            avg = np.mean(eval_scores)

            # Display the results
            print("λ = {:<15}Avg. Error: {:.3f}".format(lmbd, round(avg, 3)))

            # Store the results
            results[n].append(avg)

    # After all combinations of polynomial degrees and lambda values have been tried, plot the data
    plot_results(results, lambda_values)



if __name__ == "__main__":
    main()
