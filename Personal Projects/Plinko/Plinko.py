import turtle
import random

wn = turtle.Screen()
wn.title("Plinko by NickF502")
wn.bgcolor("black")
wn.setup(width=1.0, height=0.9, startx=0, starty=0)
wn.tracer(0)

maxh = 375
minh = -375

winValues = [10, 5, 2, 1, 0.5, 1, 2, 5, 10]

# Chip
chip = turtle.Turtle()
chip.speed(0)
chip.shape("circle")
chip.color("white")
chip.penup()
chip.goto(0, maxh)

chip.dy = -1
chip.wager = 0


# Money and Wager
money = 1000

# Functions
def move_chip_right():
    xcor = chip.xcor()
    ycor = chip.ycor()
    if ycor == maxh:
        if xcor < 200:
            xcor += 50
        chip.setx(xcor)


def move_chip_left():
    xcor = chip.xcor()
    ycor = chip.ycor()
    if ycor == maxh:
        if xcor > -200:
            xcor -= 50
        chip.setx(xcor)


def which_bounce():
    xcor = chip.xcor()
    num = random.randint(1, 100)
    if num % 2 > 0:
        if xcor < 200:
            xcor += 25
        else:
            xcor -= 25
    else:
        if xcor > -200:
            xcor -= 25
        else:
            xcor += 25
    chip.setx(xcor)


def drop_chip():
    ycor = chip.ycor()
    if ycor == maxh:
        ycor -= 0.01
        chip.sety(ycor)


def increase_wager():
    ycor = chip.ycor()
    if ycor == maxh:
        if chip.wager < money:
            chip.wager += 50
            pen2.clear()
            pen2.write("Bet: ${}".format(chip.wager), align="center", font=("Courier", 48, "bold"))


def decrease_wager():
    ycor = chip.ycor()
    if ycor == maxh:
        if chip.wager > 0:
            chip.wager -= 50
            pen2.clear()
            pen2.write("Bet: ${}".format(chip.wager), align="center", font=("Courier", 48, "bold"))


def make_divider(xcor, ycor):
    divider = turtle.Turtle()
    divider.speed(0)
    divider.shape("square")
    divider.color("white")
    divider.shapesize(stretch_wid=6, stretch_len=0.25)
    divider.penup()
    divider.goto(xcor, ycor)


def make_border(xcor, ycor):
    border = turtle.Turtle()
    border.speed(0)
    border.shape("square")
    border.color("white")
    border.shapesize(stretch_wid=77, stretch_len=0.25)
    border.penup()
    border.goto(xcor, ycor)


def make_values(val, xcor, ycor, color):
    penv = turtle.Turtle()
    penv.speed(0)
    penv.color(color)
    penv.penup()
    penv.hideturtle()
    penv.goto(xcor, ycor)
    penv.write(val, align="center", font=("Courier", 12, "bold"))


def make_pegs(xcor, ycor):
    peg = turtle.Turtle()
    peg.speed(0)
    peg.shape("circle")
    peg.color("light gray")
    peg.shapesize(stretch_wid=0.5, stretch_len=0.5)
    peg.penup()
    peg.goto(xcor, ycor)



pen = turtle.Turtle()
pen.speed(0)
pen.color("white")
pen.penup()
pen.hideturtle()
pen.goto(-500, 250)
pen.write("Money: ${}".format(int(money)), align="center", font=("Courier", 48, "bold"))


pen2 = turtle.Turtle()
pen2.speed(0)
pen2.color("white")
pen2.penup()
pen2.hideturtle()
pen2.goto(-500, 100)
pen2.write("Bet: ${}".format(chip.wager), align="center", font=("Courier", 48, "bold"))


# Game Board
make_border(225, maxh)
make_border(-225, maxh)


for x in range(-175, 176, 50):
    make_divider(x, minh)
    make_divider(x, maxh)

xForValues = -250
for value in winValues:
    if 0 < value < 1:
        clr = "red"
    elif 1 <= value <= 2:
        clr = "orange"
    elif 2 < value <= 5:
        clr = "cyan"
    elif 5 < value <= 10:
        clr = "lime"
    else:
        clr = "gold"
    xForValues += 50
    make_values(value, xForValues, minh, clr)


# Make Pegs
pegList = []
xPegList = []
yPegList = []

for y in range(226, -274, -125):    # 9 Peg Rows
    for x in range(-200, 201, 50):
        pegList.append([x, y])
        if y not in yPegList:
            yPegList.append(y)
        if x not in xPegList:
            xPegList.append(x)

for y in range(163, -213, -125):    # 8 Peg Rows
    for x in range(-175, 176, 50):
        pegList.append([x, y])
        if y not in yPegList:
            yPegList.append(y)
        if x not in xPegList:
            xPegList.append(x)

yPegList.sort()
xPegList.sort()

for [x, y] in pegList:
    make_pegs(x, y)


# Keyboard Bindings
wn.listen()
wn.onkeypress(move_chip_right, "d")
wn.onkeypress(move_chip_left, "a")
wn.onkeypress(drop_chip, "space")
wn.onkeypress(increase_wager, "w")
wn.onkeypress(decrease_wager, "s")


# Alternate Controls
wn.onkeypress(move_chip_right, "Right")
wn.onkeypress(move_chip_left, "Left")
wn.onkeypress(drop_chip, "space")
wn.onkeypress(increase_wager, "Up")
wn.onkeypress(decrease_wager, "Down")


while True:
    wn.update()


    firstTime = True
    while chip.ycor() < maxh:
        if firstTime:
            money -= chip.wager
            pen.clear()
            pen.write("Money: ${}".format(int(money)), align="center", font=("Courier", 48, "bold"))
            firstTime = False

        wn.update()

        # Make the chip fall
        if minh < chip.ycor() < maxh:
            y = chip.ycor()
            y += chip.dy
            chip.sety(y)

        # Handle Collisions
        if int(chip.ycor()) in yPegList:
            if chip.xcor() in xPegList:
                which_bounce()

        # Once the chip lands, calculate the winnings/losings
        if chip.ycor() <= minh:
            x = chip.xcor()

            if x == -200:
                money = money + chip.wager*float(winValues[0])
            elif x == -150:
                money = money + chip.wager*float(winValues[1])
            elif x == -100:
                money = money + chip.wager*float(winValues[2])
            elif x == -50:
                money = money + chip.wager*float(winValues[3])
            elif x == -0:
                money = money + chip.wager*float(winValues[4])
            elif x == 50:
                money = money + chip.wager*float(winValues[5])
            elif x == 100:
                money = money + chip.wager*float(winValues[6])
            elif x == 150:
                money = money + chip.wager*float(winValues[7])
            elif x == 200:
                money = money + chip.wager*float(winValues[8])

            # Reset the chip
            chip.setx(0)
            chip.sety(maxh)

            # Reset the Scoreboard
            pen.clear()
            pen2.clear()
            pen.write("Money: ${}".format(int(money)), align="center", font=("Courier", 48, "bold"))
            pen2.write("Bet: ${}".format(chip.wager), align="center", font=("Courier", 48, "bold"))
