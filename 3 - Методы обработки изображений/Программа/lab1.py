import numpy as np
from helperFunctions import func

if __name__ == "__main__":
    Helper= func()

    P0 = np.array([0.0, 0.0, 0.0])
    P1 = np.array([1.0, 0.0, 0.0])
    P2 = np.array([0.0, 1.0, 0.0])
    PL = np.array([0.5, 0.5, 1.0]) 
    O = Helper.normalize(np.array([0.0, 0.0, -1.0]))
    I0_RGB = np.array([1.0, 1.0, 1.0])
    x, y = 0.3, 0.4

    E = Helper.illumination(P0, P1, P2, PL, O, I0_RGB, x, y)

    print("E_RGB:", E)
