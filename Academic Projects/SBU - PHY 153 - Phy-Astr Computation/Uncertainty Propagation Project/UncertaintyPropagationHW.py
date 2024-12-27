import numpy as np
from matplotlib import pyplot as plt

f = 100.0
c = 3.0 * 10 ** 8

velocity_list = np.array([0.0001, 0.001, 0.01, 0.1, 0.3, 0.6, 0.9])
theta_list = [0, np.pi / 6.0, np.pi / 2.0]
theta_word_list = np.array(["0", "pi/6", "pi/2"])


#Calculate f' and uncertainty
def f_prime(f, v, theta):
    fprime = f * (1.0 - ((v / c) * np.cos(theta))) / np.sqrt(1.0 - (v / c) ** 2)

    unc_v = 0.1 * v
    unc_theta = 0.05 * theta
    dfdv = (c*f*(v-c*np.cos(theta))) / ((-(v-c)*(v+c))**(3.0/2.0))
    dfdtheta = (f*v*np.sin(theta)) / (c*np.sqrt(1-(v**2 / c**2)))

    unc_fprime = np.sqrt((dfdv*unc_v)**2 + (dfdtheta*unc_theta)**2)
    return (fprime, unc_fprime)


#Plot f' versus velocity for various angles
for theta in theta_list:
    fprime_list = []
    unc_fprime_list = []
    for velocity in velocity_list:
        fprime, unc_fprime = f_prime(f, velocity*c, theta)
        fprime_list.append(fprime)
        unc_fprime_list.append(unc_fprime)

    plt.title("f' vs Velocity for theta = {}".format(theta_word_list[theta_list.index(theta)]))
    plt.xlabel("Velocity (v/c) in m/s)")
    plt.ylabel("f ' in Hz")
    plt.plot(velocity_list, fprime_list)
    plt.errorbar(velocity_list, fprime_list,yerr=unc_fprime_list,fmt='o', ecolor="blue")
    plt.show()
    print()


#Print Empty Lines for Spacing
for i in range(10):
    print()


#Plot f' versus theta for various velocities
for velocity in velocity_list:
    fprime_list = []
    unc_fprime_list = []
    for theta in theta_list:
        fprime, unc_fprime = f_prime(f, velocity*c, theta)
        fprime_list.append(fprime)
        unc_fprime_list.append(unc_fprime)

    plt.title("f' vs theta for velocity = {} c".format(velocity))
    plt.xlabel("Theta in radians")
    plt.ylabel("f ' in Hz")
    plt.plot(theta_list, fprime_list)
    plt.errorbar(theta_list, fprime_list,yerr=unc_fprime_list,fmt='o', ecolor="blue")
    plt.show()
    print()
