import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import curve_fit
from scipy.stats import chi2
from scipy.stats import norm

# Put data into arrays, and arrays into a list for simple plotting via loops
cathode_materials = ["Sodium", "Platinum", "Silver", "Potassium", "Cesium"]

sodium_frequencies = np.array([4.2, 8.3, 10.4, 12.5, 14.6, 16.7, 18.8, 20.8, 22.9, 25.0, 27.1, 29.2, 31.3, 33.3, 35.4, 37.5, 39.6, 41.7, 43.8, 45.8, 47.9, 50.0])
platinum_frequencies = np.array([16.7, 18.8, 20.8, 22.9, 25.0, 27.1, 29.2, 31.3, 33.3, 35.4, 37.5, 39.6, 41.7, 43.8, 45.8, 47.9, 50.0])
silver_frequencies = np.array([10.4, 12.5, 14.6, 16.7, 18.8, 20.8, 22.9, 25.0, 27.1, 29.2, 31.3, 33.3, 35.4, 37.5, 39.6, 41.7, 43.8, 45.8, 47.9, 50.0])
potassium_frequencies = np.array([6.2, 8.3, 10.4, 12.5, 14.6, 16.7, 18.8, 20.8, 22.9, 25.0, 27.1, 29.2, 31.3, 33.3, 35.4, 37.5, 39.6, 41.7, 43.8, 45.8, 47.9, 50.0])
cesium_frequencies = np.array([2.1, 4.2, 6.2, 8.3, 10.4, 12.5, 14.6, 16.7, 18.8, 20.8, 22.9, 25.0, 27.1, 29.2, 31.3, 33.3, 35.4, 37.5, 39.6, 41.7, 43.8, 45.8, 47.9, 50.0])

frequency_list = [sodium_frequencies, platinum_frequencies, silver_frequencies, potassium_frequencies, cesium_frequencies]

sodium_kmax = np.array([1.0, 2.0, 3.2, 2.7, 5.1, 4.1, 6.1, 5.9, 8.2, 7.8, 10.3, 8.5, 10.2, 11.4, 13.0, 13.7, 12.9, 14.8, 16.1, 15.7, 17.1, 19.4])
platinum_kmax = np.array([1.9, 1.9, 1.3, 5.0, 2.8, 4.6, 3.0, 4.9, 8.0, 7.3, 9.1, 10.4, 8.6, 11.9, 13.7, 14.0, 13.1])
silver_kmax = np.array([1.5, 0.3, 2.4, 2.6, 3.1, 3.2, 5.4, 3.9, 7.5, 7.0, 8.5, 6.9, 9.4, 10.5, 12.7, 13.7, 13.6, 14.6, 15.1, 15.0])
potassium_kmax = np.array([0.9, 0.8, 1.6, 2.5, 3.7, 5.9, 4.3, 6.8, 9.1, 8.8, 8.7, 10.2, 9.4, 10.7, 13.1, 12.1, 14.3, 15.8, 15.2, 15.8, 17.6, 18.8])
cesium_kmax = np.array([0.3, 0.4, 0.4, 2.6, 3.0, 3.3, 4.1, 5.7, 7.2, 5.7, 6.5, 8.8, 8.0, 10.6, 10.4, 12.1, 11.7, 13.7, 15.9, 16.5, 15.6, 18.1, 18.2, 18.7])

kmax_list = [sodium_kmax, platinum_kmax, silver_kmax, potassium_kmax, cesium_kmax]

sodium_sigma_kmax = np.array([1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0])
platinum_sigma_kmax = np.array([1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0])
silver_sigma_kmax = np.array([1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0])
potassium_sigma_kmax = np.array([1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0])
cesium_sigma_kmax = np.array([1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0])

sigma_kmax_list = [sodium_sigma_kmax, platinum_sigma_kmax, silver_sigma_kmax, potassium_sigma_kmax, cesium_sigma_kmax]

# Constants
W_true_sodium = 2.3
W_true_platinum = 6.4
W_true_silver = 4.7
W_true_potassium = 2.2
W_true_cesium = 1.9
h_true = 0.4135667696  # *10^-14

W_true_list = [W_true_sodium, W_true_platinum, W_true_silver, W_true_potassium, W_true_cesium]

# Lists to add values into
planck_list = []
sigma_planck_list = []
work_function_list = []
sigma_work_function_list = []

# Array for the fitting line (the x in y=b1*x+b0)
fitting_line = np.arange(0.0, 50.0)


# Functions
def func(x, b0, b1):
    return b1 * x + b0


def get_gt(beta0, beta1):
    g_t = beta0 + beta1 * fitting_line
    return g_t


def get_sm(y, b0, b1, x, sigma_y):
    sm = np.sum(((y - b0 - (b1 * x)) / sigma_y) ** 2)
    return sm


def calculate_z_score(a, a_true, sigma_a):
    z = (a - a_true) / sigma_a
    return z


def p_value_from_z_score(z):
    p_value = norm.cdf(z) * 2.0
    return p_value


# --------------------------------------------------------
# Question 1: Part A
# --------------------------------------------------------

print("Question 1: Part A")
print("*" * 40, "\n")

for i in range(5):
    print("{} Trial:".format(cathode_materials[i]))
    print("-" * 40)

    popt, pcov = curve_fit(func, frequency_list[i], kmax_list[i], sigma=sigma_kmax_list[i], absolute_sigma=True, method='lm')

    work_function = popt[0]
    planck_h = popt[1]
    sigma_work_function = np.sqrt(pcov[0][0])
    sigma_planck_h = np.sqrt(pcov[1][1])

    work_function_list.append(work_function)
    sigma_work_function_list.append(sigma_work_function)
    planck_list.append(planck_h)
    sigma_planck_list.append(sigma_planck_h)

    # Print the values for h and W for each material
    print("Planck Constant (h): {:.3f} +/- {:.3f} eV".format(np.round(planck_h, 3), np.round(sigma_planck_h, 3)))
    print("Work Function (W): {:.2f} +/- {:.2f} eV".format(np.round(-work_function, 2), np.round(sigma_work_function, 2)))

    # Calculate fitted line
    gt = get_gt(work_function, planck_h)

    # Make a plot of Kmax vs Freq. for each material
    plt.title("K_max vs. Frequency of {}".format(cathode_materials[i]))
    plt.xlabel("Frequency [10^14 Hz]")
    plt.ylabel("K_max [eV]")
    plt.plot(frequency_list[i], kmax_list[i], "o")
    plt.plot(gt)
    plt.show()

    # Subtract 2 (beta0 and beta1) degrees of freedom from data points
    k = len(frequency_list[i]) - 2.0

    # Calculate Sm (Chi2)
    sm1 = get_sm(kmax_list[i], work_function, planck_h, frequency_list[i], sigma_kmax_list[i])
    print("Sm = {:.3f}".format(round(float(sm1), 3)))

    # From Sm (Chi2), calculate the p-value
    p_value1 = 1.0 - chi2.cdf(sm1, k)
    print("p-value = {:.4f} ({:.2f}%)\n\n\n".format(round(p_value1, 4), round(p_value1, 4) * 100))


# --------------------------------------------------------
# Question 1: Part B
# --------------------------------------------------------

print("Question 1: Part B")
print("*" * 40, "\n")

for i in range(5):
    print("{} Trial:".format(cathode_materials[i]))
    print("-" * 40)

    # Calculate the z-score for each material's Work Function
    z_score_w = calculate_z_score(-1.0 * work_function_list[i], W_true_list[i], sigma_work_function_list[i])
    print("z-score of Work Function: {:.3f}".format(round(z_score_w, 3)))

    # Calculate the p-value from each z-score for each material's Work Function
    p_val = p_value_from_z_score(z_score_w)
    print("p-value = {:.4f} ({:.2f}%)\n\n\n".format(round(p_val, 4), round(p_val, 6) * 100))


# --------------------------------------------------------
# Question 2
# --------------------------------------------------------

print("Question 2:")
print("*" * 40, "\n")

average_h_value = np.average(planck_list)

sigma_average_h_value = 0.0
for value in sigma_planck_list:
    sigma_average_h_value = np.sqrt(np.sum(value**2))

z_average_h = calculate_z_score(average_h_value, h_true, sigma_average_h_value)
p_average_h = p_value_from_z_score(z_average_h)

print("Average Planck Constant Trial:")
print("-" * 40)
print("Planck Constant (h): {:.3f} +/- {:.3f} eV".format(round(average_h_value, 3), round(sigma_average_h_value, 3)))
print("The True value for Planck's Constant is {} eV".format(h_true))
print("z-score for Planck Constant: {:.3f}".format(round(z_average_h, 3)))
print("p-value of Planck Constant = {:.4f} ({:.2f}%)\n\n\n".format(round(p_average_h, 4), round(p_average_h, 6) * 100))

for i in range(5):
    print("{} Trial:".format(cathode_materials[i]))
    print("-" * 25)

    # Print the Planck Constant for each material
    print("Planck Constant (h): {:.3f} +/- {:.3f} eV".format(round(planck_list[i], 3), round(sigma_planck_list[i], 3)))

    # Calculate the z-score for each material's Planck Value
    z_score_h = calculate_z_score(planck_list[i], h_true, sigma_planck_list[i])
    print("z-score for Planck Constant: {:.3f}".format(round(z_score_h, 3)))

    # Calculate the p-value from each z-score for each material's Planck Value
    p_val = p_value_from_z_score(z_score_h)
    print("p-value of Planck Constant = {:.4f} ({:.2f}%)\n\n\n".format(round(p_val, 4), round(p_val, 6) * 100))
