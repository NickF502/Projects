class Player:
    def __init__(self, name, number, position):
        self.name = name
        self.number = number
        self.position = position


class Batter(Player):
    def __init__(self, name, number, position, krate, bbrate, babip, slg):
        super().__init__(name, number, position)
        self.krate = krate
        self.slg = slg
        self.bbrate = bbrate
        self.babip = babip


class Pitcher(Player):
    def __init__(self, name, number, position, kratea, bbratea, babipa, slga):
        super().__init__(name, number, position)
        self.kratea = kratea
        self.slga = slga
        self.bbratea = bbratea
        self.babipa = babipa


mets_batters = [
    Batter("Brandon Nimmo", 9, 8, .220, .136, .330, 1.67),
    Batter("Starling Marte", 6, 9, .178, .057, .333, 1.60),
    Batter("Francisco Lindor", 12, 6, .163, .091, .280, 1.79),
    Batter("Pete Alonso", 20, 3, .227, .099, .277, 2.07),
    Batter("Daniel Vogelbach", 32, 0, .250, .157, .252, 1.93),
    Batter("Mark Canha", 19, 7, .208, .118, .294, 1.70),
    Batter("Jeff McNeil", 1, 4, .125, .067, .327, 1.52),
    Batter("Eduardo Escobar", 10, 5, .204, .076, .282, 1.82),
    Batter("Tomás Nido", 3, 2, .267, .047, .278, 1.43),
    ]

mets_pitchers = [
    Pitcher("Jacob deGrom", 48, 1, .351, .053, .274, 1.65),
    Pitcher("Adam Ottavino", 0, 1, .308, .116, .290, 1.56),
    Pitcher("Edwin Díaz", 39, 1, .422, .083, .331, 1.61),
    ]


mariners_batters = [
    Batter("Julio Rodríguez", 44, 8, .273, .071, .343, 1.75),
    Batter("Ty France", 23, 3, .177, .064, .325, 1.56),
    Batter("Mitch Haniger", 17, 0, .239, .092, .302, 1.86),
    Batter("Jesse Winker", 27, 7, .168, .129, .299, 1.71),
    Batter("Eugenio Suárez", 28, 5, .288, .108, .285, 2.02),
    Batter("Adam Frazier", 26, 4, .125, .075, .303, 1.47),
    Batter("Cal Raleigh", 29, 2, .319, .071, .248, 2.05),
    Batter("J.P. Crawford", 3, 6, .173, .094, .298, 1.45),
    Batter("Sam Haggerty", 0, 9, .286, .062, .335, 1.62),
    ]

mariners_pitchers = [
    Pitcher("Luis Castillo", 21, 1, .260, .087, .290, 1.65),
    Pitcher("Erik Swanson", 50, 1, .255, .055, .260, 1.89),
    Pitcher("Paul Sewald", 37, 1, .295, .081, .271, 1.81)
    ]

mets = mets_batters + mets_pitchers
mariners = mariners_batters + mariners_pitchers

