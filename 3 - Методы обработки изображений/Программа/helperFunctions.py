import math
from Vector import Vector

vector = Vector()
class func:
    # векторное произведение
    def cross_product(self, vector1, vector2):
        res = [
            vector1[1] * vector2[2] - vector1[2] * vector2[1],
            vector1[2] * vector2[0] - vector1[0] * vector2[2],
            vector1[0] * vector2[1] - vector1[1] * vector2[0] 
        ]
        return res
    # Координата Pt
    def PT(self, P0, P1, P2, x, y):
        edge1 = vector.normalize(vector.minus(P1, P0))
        edge2 = vector.normalize(vector.minus(P2, P0))
        scaled_edge1 = vector.scalar_multiply(x, edge1)
        scaled_edge2 = vector.scalar_multiply(y, edge2)
        return vector.sum(vector.sum(P0, scaled_edge1), scaled_edge2)

    def illumination(self, N, s, O, I0_RGB):
        # E = I0_RGB * cos_theta * cos_alpha / R^2
        # cos_theta = s.O / normalize(s)
        # cos_alpha = s.N / normalize(s)
    
        R2 = vector.dot_product(s, s)
        s_length = vector.length(s)
        cos_alpha = vector.dot_product(s, N) / s_length
        cos_theta = vector.dot_product(s, O) / s_length
        # условие
        if cos_alpha < 0 or cos_theta < 0:
            return[0,0,0]
        # освещенность
        E_RGB = []
        for i in range(len(I0_RGB)):
            value = round((I0_RGB[i] * cos_theta * cos_alpha) / R2,2)
            E_RGB.append(value)  
        return E_RGB

    
    # средний вектор
    def AverageVector(self, vector1, vector2):
        res = vector.sum(vector1, vector2)
        len_sum = vector.length(res)
        return [res[i] / len_sum for i in range(len(res))]
    
    # Яркость
    def brightness(self, P0, P1, P2, PL1, PL2, O1, O2, I01_RGB, I02_RGB, x, y, V, K_RGB, kd, ks, ke):
        N = vector.normal(P0, P1, P2)
        PT = self.PT(P0, P1, P2, x, y)
        s1 = vector.minus(PT, PL1)
        s2 = vector.minus(PT, PL2)
        v = vector.minus(PT, V)
        if vector.dot_product(N, v) < 0:
            N = vector.minus([0,0,0], N)

        E1_RGB = self.illumination(N, s1, O1, I01_RGB)
        E2_RGB = self.illumination(N, s2, O2, I02_RGB)
        
        h1 = self.AverageVector(v, s1)
        h2 = self.AverageVector(v, s2)

        coeff1 = kd + ks * max(0, math.pow(vector.dot_product(h1, N), ke))
        coeff2 = kd + ks * max(0, math.pow(vector.dot_product(h2, N), ke))

        term1 = vector.scalar_multiply(coeff1, self.cross_product(E1_RGB, K_RGB))
        term2 = vector.scalar_multiply(coeff2, self.cross_product(E1_RGB, K_RGB))
        L_RGB = vector.scalar_multiply(1/math.pi, self.sum(term1,term2))
            
        
        return L_RGB
    
    # плюс
    def sum(self, vector1, vector2):
        res = []
        for i in range(len(vector1)):
            res.append(vector1[i] + vector2[i])
        return res
        