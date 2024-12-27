# you should fill in the functions in this file,
# do NOT change the name, input and output of these functions

import numpy as np
import time
import matplotlib.pyplot as plt

# first function to fill, compute distance matrix using loops
def compute_distance_naive(X):
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


# second function to fill, compute distance matrix without loops
def compute_distance_smart(X):

    # Compute the dot product of X and its transpose
    dp = np.dot(X, X.T)     # dp's ij-th entry stores xi dot xj

    # Store the diagonal components of the matrix
    square_norm = np.diag(dp)   # Represents values of ||xi||^2 for all i; used in the next calculation

    # Calculate pairwise Euclidean distance using the given formula
    # sqrt( ||xi||^2 - 2*xi.T*xj + ||xi||^2)
    M = np.sqrt(square_norm[:, np.newaxis] - 2*dp + square_norm[np.newaxis, :])

    return M

# third function to fill, compute correlation matrix using loops
def compute_correlation_naive(X):
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
            M[i, j] = covariance_matrix[i, j] / (sigmas[i] * sigmas[j])

    return M

# fourth function to fill, compute correlation matrix without loops
def compute_correlation_smart(X):
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

def main():
    print('starting comparing distance computation .....')
    np.random.seed(100)
    params = range(10,141,10)   # different param setting
    nparams = len(params)       # number of different parameters

    perf_dist_loop = np.zeros([10,nparams])  # 10 trials = 10 rows, each parameter is a column
    perf_dist_cool = np.zeros([10,nparams])
    perf_corr_loop = np.zeros([10,nparams])  # 10 trials = 10 rows, each parameter is a column
    perf_corr_cool = np.zeros([10,nparams])

    counter = 0

    for ncols in params:
        nrows = ncols * 10

        print("matrix dimensions: ", nrows, ncols)

        for i in range(10):
            X = np.random.rand(nrows, ncols)   # random matrix

            # compute distance matrices
            st = time.time()
            dist_loop = compute_distance_naive(X)
            et = time.time()
            perf_dist_loop[i,counter] = et - st              # time difference

            st = time.time()
            dist_cool = compute_distance_smart(X)
            et = time.time()
            perf_dist_cool[i,counter] = et - st

            assert np.allclose(dist_loop, dist_cool, atol=1e-06) # check if the two computed matrices are identical all the time

            # compute correlation matrices
            st = time.time()
            corr_loop = compute_correlation_naive(X)
            et = time.time()
            perf_corr_loop[i,counter] = et - st              # time difference

            st = time.time()
            corr_cool = compute_correlation_smart(X)
            et = time.time()
            perf_corr_cool[i,counter] = et - st

            assert np.allclose(corr_loop, corr_cool, atol=1e-06) # check if the two computed matrices are identical all the time

        counter = counter + 1

    mean_dist_loop = np.mean(perf_dist_loop, axis = 0)    # mean time for each parameter setting (over 10 trials)
    mean_dist_cool = np.mean(perf_dist_cool, axis = 0)
    std_dist_loop = np.std(perf_dist_loop, axis = 0)      # standard deviation
    std_dist_cool = np.std(perf_dist_cool, axis = 0)

    plt.figure(1)
    plt.errorbar(params, mean_dist_loop[0:nparams], yerr=std_dist_loop[0:nparams], color='red',label = 'Loop Solution for Distance Comp')
    plt.errorbar(params, mean_dist_cool[0:nparams], yerr=std_dist_cool[0:nparams], color='blue', label = 'Matrix Solution for Distance Comp')
    plt.xlabel('Number of Cols of the Matrix')
    plt.ylabel('Running Time (Seconds)')
    plt.title('Comparing Distance Computation Methods')
    plt.legend()
    plt.savefig('CompareDistanceCompFig.pdf')
    # plt.show()    # uncomment this if you want to see it right way
    print("result is written to CompareDistanceCompFig.pdf")

    mean_corr_loop = np.mean(perf_corr_loop, axis = 0)    # mean time for each parameter setting (over 10 trials)
    mean_corr_cool = np.mean(perf_corr_cool, axis = 0)
    std_corr_loop = np.std(perf_corr_loop, axis = 0)      # standard deviation
    std_corr_cool = np.std(perf_corr_cool, axis = 0)

    plt.figure(2)
    plt.errorbar(params, mean_corr_loop[0:nparams], yerr=std_corr_loop[0:nparams], color='red',label = 'Loop Solution for Correlation Comp')
    plt.errorbar(params, mean_corr_cool[0:nparams], yerr=std_corr_cool[0:nparams], color='blue', label = 'Matrix Solution for Correlation Comp')
    plt.xlabel('Number of Cols of the Matrix')
    plt.ylabel('Running Time (Seconds)')
    plt.title('Comparing Correlation Computation Methods')
    plt.legend()
    plt.savefig('CompareCorrelationCompFig.pdf')
    # plt.show()    # uncomment this if you want to see it right way
    print("result is written to CompareCorrelationCompFig.pdf")

if __name__ == "__main__": main()
