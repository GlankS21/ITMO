import math
class Vector:
    # длина вектора
    def length(self, vector):
        res = 0
        for i in vector:
            res += i * i
        return res ** 0.5
    
    # Нормализация вектора
    def normalize(self, vector):
        length = self.length(vector)
        res = []
        for i in vector:
            res.append(i / length)
        return res
    
    # минут
    def minus(self, vector1, vector2):
        res = []
        for i in range(len(vector1)):
            res.append(vector1[i] - vector2[i])
        return res
    
    # плюс
    def sum(self, vector1, vector2):
        res = []
        for i in range(len(vector1)):
            res.append(vector1[i] + vector2[i])
        return res
    
    # векторное произведение
    def cross_product(self, vector1, vector2):
        res = [
            vector1[1] * vector2[2] - vector1[2] * vector2[1],
            vector1[2] * vector2[0] - vector1[0] * vector2[2],
            vector1[0] * vector2[1] - vector1[1] * vector2[0] 
        ]
        return res

    # Скалярное произведение
    def dot_product(self, vector1, vector2):
        res = 0
        for i in range(len(vector1)):
            res += vector1[i] * vector2[i]
        return res

    # Умножение на число
    def scalar_multiply(self, number, vector):
        res = []
        for i in vector:
            res.append(number * i)
        return res
    
    # Нормаль к поверхности
    def normal(self, P0, P1, P2):
        edge1 = self.minus(P1, P0)
        edge2 = self.minus(P2, P0)
        cross = self.cross_product(edge2, edge1)
        return self.normalize(cross)
 
    # средний вектор
    def AverageVector(self, vector1, vector2):
        res = self.sum(vector1, vector2)
        len_sum = self.length(res)
        return [res[i] / len_sum for i in range(len(res))]
        