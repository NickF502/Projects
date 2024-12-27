# you should fill in the functions in this file,
# do NOT change the name, input and output of these functions

import numpy as np
import time
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris, load_digits, load_breast_cancer

def compute_distance_naive(X):
    """
    Calculate the distance matrix using loops
    :param X: matrix to perform calculations on
    :return: the completed matrix
    """

    numRows = X.shape[0]  # Number of Rows
    M = np.zeros([numRows, numRows])    # Initialize M to 0

    # Loop through each element, computing each vector against each other
    for i in range(numRows):
        for j in range(numRows):
            xi = X[i, :]        # Take the entire ith row/vector
            xj = X[j, :]        # Take the entire jth row/vector

            # Compute the distance between the vectors
            dist = np.sqrt(np.sum( (xi - xj)**2) )  # sqrt of the sum of squares, standard distance formula

            M[i, j] = dist  # Assign M's ij-th entry to the distance between xi and xj

    return M


def compute_distance_smart(X):
    """
    Calculate the distance matrix using numpy functions
    :param X: matrix to perform calculations on
    :return: the completed matrix
    """

    # Compute the dot product of X and its transpose
    dp = np.dot(X, X.T)     # dp's ij-th entry stores xi dot xj

    # Store the diagonal components of the matrix
    square_norm = np.diag(dp)   # Represents values of ||xi||^2 for all i; used in the next calculation

    # Calculate pairwise Euclidean distance using the given formula
    # sqrt( ||xi||^2 - 2*xi.T*xj + ||xi||^2)
    M = np.sqrt(square_norm[:, np.newaxis] - 2*dp + square_norm[np.newaxis, :])

    return M

def compute_correlation_naive(X):
    """
    Calculate the correlation matrix using loops
    :param X: matrix to perform calculations on
    :return: the completed matrix
    """

    N, D = X.shape  # Number of rows/cols
    M = np.zeros([D, D])    # Initialize M to 0

    # Compute the mean down each column; used in covariance matrix calculation
    mu = np.mean(X, axis=0)

    # Initialize the covariance matrix to zeroes
    covariance_matrix = np.zeros([D, D])

    # Compute covariance matrix by looping through elements
    for i in range(D):
        for j in range(D):
            xi = X[:, i]    # Take the entire i-th column
            xj = X[:, j]    # Take the entire j-th column

            # Calculate covariance according to given formula
            s = (1 / (N-1)) * np.sum((xi - mu[i]) * (xj - mu[j]))
            covariance_matrix[i, j] = s   # Assign the ij-th entry to the sample variance

    # Compute standard deviation according to given formula
    sigmas = np.sqrt(np.diag(covariance_matrix))

    # Compute correlation matrix M according to the given formula
    for i in range(D):
        for j in range(D):
            if sigmas[i] == 0 or sigmas[j] == 0:    # Handle div by 0
                continue
            else:
                M[i, j] = covariance_matrix[i, j] / (sigmas[i] * sigmas[j])

    return M

def compute_correlation_smart(X):
    """
    Calculate the correlation matrix using numpy functions
    :param X: matrix to perform calculations on
    :return: the completed matrix
    """

    N, D = X.shape  # Number of rows/cols

    # Compute the mean down each column; used in covariance matrix calculation
    mu = np.mean(X, axis=0)

    # Shift the matrix by the average to "normalize" it
    adj_X = X - mu

    # Compute the covariance matrix using dot product
    covariance_matrix = np.dot(adj_X.T, adj_X) / (N - 1)

    # Compute standard deviation for each column according to the given formula
    sigmas = np.sqrt(np.diag(covariance_matrix))

    # Compute the product of the standard deviation pairs; the denominator
    sigmas_outer = np.outer(sigmas, sigmas)

    # Calculate the correlation by dividing the covariance matrix by sigmas_outers
    M = covariance_matrix / sigmas_outer

    return M



def run_trial(x):
    """
    Function to calculate the time it takes to compute the distance and correlation matrices using both methods
    :param x: matrix to perform calculations on
    :return: the times it takes for each computation to complete
    """

    # Compute distance using loops and store time
    st = time.time()
    compute_distance_naive(x)
    et = time.time()
    distance_loop_times = et - st

    # Compute distance without using loops and store time
    st = time.time()
    compute_distance_smart(x)
    et = time.time()
    distance_no_loop_times = et - st

    # Compute correlation using loops and store time
    st = time.time()
    compute_correlation_naive(x)
    et = time.time()
    correlation_loop_times = et - st

    # Compute correlation without using loops and store time
    st = time.time()
    compute_correlation_smart(x)
    et = time.time()
    correlation_no_loop_times = et - st

    # Return the times for each of the four calculation
    return distance_loop_times, distance_no_loop_times, correlation_loop_times, correlation_no_loop_times


def computeMatrices():
    """
    Function to generate matrices of varying dimensions, perform the four calculations on them, and store the times
    :return: the matrices generated to be used in another function
    """

    print('starting comparing distance computation .....')
    np.random.seed(100)
    params = range(10, 141, 10)  # different param setting (stores multiples of 10 from 10 to 140)
    nparams = len(params)  # number of different parameters (stores 14)


    matrices_used = []      # List of matrices generated
    distance_loop_times = []            # List of computation times for calculating distance with loops
    distance_no_loop_times = []         # List of computation times for calculating distance with numpy
    correlation_loop_times = []         # List of computation times for calculating correlation with loops
    correlation_no_loop_times = []      # List of computation times for calculating correlation with numpy


    perf_dist_loop = np.zeros([10, nparams])  # 10 trials = 10 rows, each parameter is a column
    perf_dist_cool = np.zeros([10, nparams])
    perf_corr_loop = np.zeros([10, nparams])  # 10 trials = 10 rows, each parameter is a column
    perf_corr_cool = np.zeros([10, nparams])

    counter = 0


    for ncols in params:
        nrows = ncols * 10  # Used to create a 10N * N matrix (N = ncols)

        print("matrix dimensions: ", nrows, ncols)

        for i in range(10):
            X = np.random.rand(nrows, ncols)  # random matrix (size 10N x N)
            matrices_used.append(X)     # Store the matrix used

            # Compute distance using loops and store time - Problem 1
            st = time.time()
            dist_loop = compute_distance_naive(X)
            et = time.time()
            perf_dist_loop[i, counter] = et - st  # time difference
            distance_loop_times.append(et - st)

            # Compute distance without using loops and store time - Problem 1
            st = time.time()
            dist_cool = compute_distance_smart(X)
            et = time.time()
            perf_dist_cool[i, counter] = et - st
            distance_no_loop_times.append(et - st)

            # check if the two computed matrices are identical all the time
            assert np.allclose(dist_loop, dist_cool, atol=1e-06)



            # Compute correlation using loops and store time - Problem 2
            st = time.time()
            corr_loop = compute_correlation_naive(X)
            et = time.time()
            perf_corr_loop[i, counter] = et - st  # time difference
            correlation_loop_times.append(et - st)

            # Compute correlation without using loops and store time - Problem 2
            st = time.time()
            corr_cool = compute_correlation_smart(X)
            et = time.time()
            perf_corr_cool[i, counter] = et - st
            correlation_no_loop_times.append(et - st)

            # check if the two computed matrices are identical all the time
            assert np.allclose(corr_loop, corr_cool, atol=1e-06)

        counter = counter + 1   # Increment counter


    mean_dist_loop = np.mean(perf_dist_loop, axis=0)  # mean time for each parameter setting (over 10 trials)
    mean_dist_cool = np.mean(perf_dist_cool, axis=0)
    std_dist_loop = np.std(perf_dist_loop, axis=0)  # standard deviation
    std_dist_cool = np.std(perf_dist_cool, axis=0)

    # Create the plots
    plt.figure(1)
    plt.errorbar(params, mean_dist_loop[0:nparams], yerr=std_dist_loop[0:nparams], color='red',
                 label='Loop Solution for Distance Comp')
    plt.errorbar(params, mean_dist_cool[0:nparams], yerr=std_dist_cool[0:nparams], color='blue',
                 label='Matrix Solution for Distance Comp')
    plt.xlabel('Number of Cols of the Matrix')
    plt.ylabel('Running Time (Seconds)')
    plt.title('Comparing Distance Computation Methods')
    plt.legend()
    plt.savefig('CompareDistanceCompFig.pdf')
    #plt.show()    # uncomment this if you want to see it right way
    print("result is written to CompareDistanceCompFig.pdf")

    mean_corr_loop = np.mean(perf_corr_loop, axis=0)  # mean time for each parameter setting (over 10 trials)
    mean_corr_cool = np.mean(perf_corr_cool, axis=0)
    std_corr_loop = np.std(perf_corr_loop, axis=0)  # standard deviation
    std_corr_cool = np.std(perf_corr_cool, axis=0)

    plt.figure(2)
    plt.errorbar(params, mean_corr_loop[0:nparams], yerr=std_corr_loop[0:nparams], color='red',
                 label='Loop Solution for Correlation Comp')
    plt.errorbar(params, mean_corr_cool[0:nparams], yerr=std_corr_cool[0:nparams], color='blue',
                 label='Matrix Solution for Correlation Comp')
    plt.xlabel('Number of Cols of the Matrix')
    plt.ylabel('Running Time (Seconds)')
    plt.title('Comparing Correlation Computation Methods')
    plt.legend()
    plt.savefig('CompareCorrelationCompFig.pdf')
    #plt.show()    # uncomment this if you want to see it right way
    print("result is written to CompareCorrelationCompFig.pdf")

    # Return the matrices used to use in entropy
    return matrices_used, (distance_loop_times, distance_no_loop_times, correlation_loop_times, correlation_no_loop_times)



def entropyComputation(matrices_used):

    def compute_entropy_loop(X):
        N = np.sum(X)  # use np.sum rather than sum, check the difference
        if N == 0:  # if N is zero, quit right way
            return 0
        entropy = 0.0  # initialize entropy
        for k in range(len(X)):
            for j in range(len(X[0])):
                if X[k, j] == 0:  # if zero prob, skip
                    continue

                prob = float(X[k, j]) / N  # remember to convert an inter to a float first,
                # otherwise the result will be rounded
                logp = np.log2(prob)  # take log base 2
                entropy = entropy - prob * logp

        return entropy



    def compute_entropy_cool(X):
        N = np.sum(X)
        if N == 0:
            return 0

        prob = X.astype(float) / N  # probability matrix, conver to float matrix using astype
        tmpM = np.zeros(prob.shape)
        tmpM[X != 0] = prob[X != 0] * np.log2(prob[X != 0])  # select entries for element-wise product
        return - np.sum(tmpM)




    print('starting running .....')
    np.random.seed(100)
    params = range(10, 141, 10)  # different param setting
    nparams = len(params)  # number of different parameters

    perf_loop = np.zeros([10, nparams])  # 10 trials = 10 rows, each parameter is a column
    perf_cool = np.zeros([10, nparams])

    counter = 0

    for ncols in params:
        nrows = ncols * 10

        print("matrix dimensions: ", nrows, ncols)

        for i in range(10):
            X = matrices_used[i + ncols - 10]
            # you need to use random.rand(...) for float matrix

            st = time.time()
            entropy_loop = compute_entropy_loop(X)
            et = time.time()
            perf_loop[i, counter] = et - st  # time difference

            st = time.time()
            entropy_cool = compute_entropy_cool(X)
            et = time.time()
            perf_cool[i, counter] = et - st

            assert np.isclose(entropy_loop, entropy_cool, atol=1e-06)

        counter = counter + 1

    mean_loop = np.mean(perf_loop, axis=0)  # mean time for each parameter setting (over 10 trials)
    mean_cool = np.mean(perf_cool, axis=0)

    std_loop = np.std(perf_loop, axis=0)  # standard deviation
    std_cool = np.std(perf_cool, axis=0)

    import matplotlib.pyplot as plt
    plt.errorbar(params, mean_loop[0:nparams], yerr=std_loop[0:nparams], color='red', label='Loop Solution')
    plt.errorbar(params, mean_cool[0:nparams], yerr=std_cool[0:nparams], color='blue', label='Matrix Solution')
    plt.xlabel('Number of Cols of the Matrix')
    plt.ylabel('Running Time (Seconds)')
    plt.legend()
    plt.savefig('CompareEntropyFig.pdf')
    #plt.show()  # uncomment this if you want to see it right way

    print("result is written to CompareEntropyFig.pdf")



def main():

    # Problems 1 and 2
    print("Problems 1 & 2")
    matrices_used, comp_times = computeMatrices()
    entropyComputation(matrices_used)

    # Problem 3
    print("Problem 3")

    print("Iris:")
    d = load_iris().data
    dist_loop_time, dist_no_loop_time, corr_loop_time, corr_no_loop_time = run_trial(d)
    print("Distance Loop Time - ", dist_loop_time)
    print("Distance No Loop Time - ", dist_no_loop_time)
    print("Correlation Loop Time - ", corr_loop_time)
    print("Correlation No Loop Time - ", corr_no_loop_time)
    print("----------------------------------")

    print("Breast Cancer:")
    d = load_breast_cancer().data
    dist_loop_time, dist_no_loop_time, corr_loop_time, corr_no_loop_time = run_trial(d)
    print("Distance Loop Time - ", dist_loop_time)
    print("Distance No Loop Time - ", dist_no_loop_time)
    print("Correlation Loop Time - ", corr_loop_time)
    print("Correlation No Loop Time - ", corr_no_loop_time)
    print("----------------------------------")


    print("Digits:")
    d = load_digits().data
    dist_loop_time, dist_no_loop_time, corr_loop_time, corr_no_loop_time = run_trial(d)
    print("Distance Loop Time - ", dist_loop_time)
    print("Distance No Loop Time - ", dist_no_loop_time)
    print("Correlation Loop Time - ", corr_loop_time)
    print("Correlation No Loop Time - ", corr_no_loop_time)
    print("----------------------------------")


if __name__ == "__main__":
    main()
