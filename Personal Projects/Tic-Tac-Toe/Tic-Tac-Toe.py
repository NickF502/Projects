import turtle
import random


combo1 = [[-200, 200], [0, 200], [200, 200]]
combo2 = [[-200, 0], [0, 0], [200, 0]]
combo3 = [[-200, -200], [0, -200], [200, -200]]
combo4 = [[-200, 200], [-200, 0], [-200, -200]]
combo5 = [[0, 200], [0, 0], [0, -200]]
combo6 = [[200, 200], [200, 0], [200, -200]]
combo7 = [[-200, 200], [0, 0], [200, -200]]
combo8 = [[200, 200], [0, 0], [-200, -200]]

comboList = [combo1, combo2, combo3, combo4, combo5, combo6,  combo7, combo8, ]


usedSpots = []
circleSpots = []
squareSpots = []
gridPoints = []


wn = turtle.Screen()
wn.title("Tic-Tac-Toe by NickF502")
wn.bgcolor("gray")
wn.setup(width=600, height=600)
wn.tracer(0)


flip = random.randint(1, 100)
if flip % 2 > 0:
    isCircleTurn = True
    bcol = "red"
    markshp = "circle"
    markcol = "red"
else:
    isCircleTurn = False
    bcol = "blue"
    markshp = "square"
    markcol = "blue"


# Make the Board
counter = 1
while counter < 10:
    for x in range(-200, 201, 200):
        for y in range(-200, 201, 200):
            square = turtle.Turtle()
            square.shape("square")
            square.color("light gray")
            square.speed(0)
            square.goto(0, 0)
            square.shapesize(stretch_len=30, stretch_wid=30)
            counter += 1


# Convert Coords into Grid Pos.
counter = 1
while counter < 10:
    for x in range(-200, 201, 200):
        for y in range(-200, 201, 200):
            gridPoints.append([x, y])
            counter += 1


# Make Borders
def make_border(xcor, ycor, strchlen, strchwid):
    border = turtle.Turtle()
    border.shape("square")
    border.color("black")
    border.speed(0)
    border.goto(xcor, ycor)
    border.shapesize(stretch_len=strchlen, stretch_wid=strchwid)
    return border


line1 = make_border(-100, 0, 0.2, 30)
line2 = make_border(100, 0, 0.2, 30)
line3 = make_border(0, -100, 30, 0.2)
line4 = make_border(0, 100, 30, 0.2)
line5 = make_border(-300, 0, 0.2, 30)
line6 = make_border(300, 0, 0.2, 30)
line7 = make_border(0, -300, 30, 0.2)
line8 = make_border(0, 300, 30, 0.2)

# Make Highlighter
highlighter = turtle.Pen()
highlighter.speed(0)
highlighter.shapesize(stretch_len=9.7, stretch_wid=9.7)
highlighter.shape("square")
highlighter.fillcolor("yellow")
highlighter.penup()
highlighter.goto(0, 0)


# Make Pen for Marker
marker = turtle.Pen()
marker.speed(0)
marker.shape(markshp)
marker.color(markcol)
marker.penup()
marker.goto(0, 0)


# Movement Functions
def move_left():
    xcor = marker.xcor()
    if xcor > -200:
        xcor -= 200
    marker.setx(xcor)


def move_right():
    xcor = marker.xcor()
    if xcor < 200:
        xcor += 200
    marker.setx(xcor)


def move_up():
    ycor = marker.ycor()
    if ycor < 200:
        ycor += 200
    marker.sety(ycor)


def move_down():
    ycor = marker.ycor()
    if ycor > -200:
        ycor -= 200
    marker.sety(ycor)


# Place Object Functions
def make_circle(xcor, ycor):
    penc = turtle.Pen()
    penc.shape("circle")
    penc.shapesize(stretch_len=5, stretch_wid=5)
    penc.color("red")
    penc.goto(xcor, ycor)
    penc.pendown()
    usedSpots.append([xcor, ycor])


def make_square(xcor, ycor):
    penn = turtle.Pen()
    penn.shape("square")
    penn.shapesize(stretch_len=5, stretch_wid=5)
    penn.color("blue")
    penn.goto(xcor, ycor)
    penn.pendown()
    usedSpots.append([xcor, ycor])


def place_symbol():
    global isCircleTurn
    if [marker.xcor(), marker.ycor()] in usedSpots:
        pass
    else:
        if isCircleTurn:
            make_circle(marker.xcor(), marker.ycor())
            circleSpots.append([marker.xcor(), marker.ycor()])
            isCircleTurn = False
        else:
            make_square(marker.xcor(), marker.ycor())
            squareSpots.append([marker.xcor(), marker.ycor()])
            isCircleTurn = True


# Running Game
while True:
    wn.update()

    highlighter.goto(marker.xcor(), marker.ycor())

    # Get Game Controls
    wn.listen()
    wn.onkeypress(move_left, "a")
    wn.onkeypress(move_right, "d")
    wn.onkeypress(move_up, "w")
    wn.onkeypress(move_down, "s")
    wn.onkeypress(place_symbol, "space")

# Turn Signal
    if isCircleTurn:
        bcol = "red"
        marker.shape()
        marker.shape("circle")
        marker.color(bcol)
    else:
        bcol = "blue"
        marker.shape("square")
        marker.color(bcol)

    # Check for Victory
    counterC = 0
    counterS = 0

    for combo in comboList:
        counterC = 0
        counterS = 0
        for point in circleSpots:
            if point in combo:
                counterC += 1
                if counterC == 3:
                    print("Red Circles Win!")
                    exit(1)
        for point in squareSpots:
            if point in combo:
                counterS += 1
                if counterS == 3:
                    print("Blue Squares Win!")
                    exit(2)

    # Check for Tie Game
    if len(usedSpots) == 9:
        print("Game has ended in a tie!")
        exit(3)


