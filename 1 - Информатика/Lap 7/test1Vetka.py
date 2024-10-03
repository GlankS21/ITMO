#!/usr/bin/env python
# coding=utf-8
import sys
import inkex
from lxml import etree
import math 
import random
def mytree(x0, y0, l, a0, a): 
    a1 = a0 + a/random.randint(2,4) + 0.3
    a2 = a0 - a/random.randint(2,3) + 0.3
    x1 = x0 + l*math.cos(a1)
    y1 = y0 - l*math.sin(a1)
    x2 = x0 + l*math.cos(a2)
    y2 = y0 - l*math.sin(a2)
    s = 'M' + str(x0) + ',' + str(y0) + 'L' +  str(x1) + ',' + str(y1) + 'M' + str(x0) + ',' + str(y0) + 'L' +  str(x2) + ',' + str(y2)
    if l > 2:
        if random.randint(0,100) > 20:
            s = s + ' ' + mytree(x1,y1, 3*l//4, a1, a) 
        if random.randint(0,100) > 20:
            s = s + ' ' + mytree(x2, y2, 3*l//4, a2,a)
    return s 
def draw_SVG_square(a,x,y,cur):
    style = {'stroke': '#000000', 'stroke-width': '0.2', 'fill': 'none'}
    elem = cur.add(inkex.PathElement())
    elem.update(**{
        'style': style,
        'inkscape:label': 'MySquare',
         'd': mytree(x,y,a, 3.14/2, 1.5)})
    return elem
class MySquare(inkex.EffectExtension):
    def add_arguments(self, pars):
        pars.add_argument("--s_l", type=float, default=100.0, help="Side Length")
        pars.add_argument("--c_x", type=float, default=0.0, help="X coord.")
        pars.add_argument("--c_y", type=float, default=0.0, help="Y coord.")
    def effect(self):
        cur = self.svg.get_current_layer()
        a = self.svg.unittouu(str(self.options.s_l) + 'px')
        x = self.svg.unittouu(str(self.options.c_x) + 'px')
        y = self.svg.unittouu(str(self.options.c_y) + 'px')
        draw_SVG_square(a,x,y,cur) 
if __name__ == '__main__':
    MySquare().run()
