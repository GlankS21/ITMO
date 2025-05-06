import numpy as np
from helperFunctions import func
from Vector import Vector

if __name__ == "__main__":
    Helper= func()
    Vector = Vector()

    P0 = [0, 0, 0]
    P1 = [1, 0, 0]
    P2 = [0, 1, 0]
    PL1 = [1, 1, 1]
    PL2 = [-1, 1, 1]
    
    O1 = Vector.normalize([-1, -1, -1]) 
    O2 = Vector.normalize([1, -1, -1])
    I01_RGB = [2.0, 2.0, 2.0]
    I02_RGB = [2.0, 2.0, 2.0]
    x, y = 0, 0.4
    V = Vector.normalize([0, 0.5, 0.5])
    K_RGB = [0.5, 0.5, 0.5]
    kd = 0.7
    ks = 0.3
    ke = 10
    while x < 0.5:
        print(round(x,1),",",y)
        brightness = Helper.brightness(P0, P1, P2, PL1, PL2, O1, O2, I01_RGB, I02_RGB, x, y, V, K_RGB, kd, ks, ke)
        print("Brightness:", brightness)
        x += 0.1