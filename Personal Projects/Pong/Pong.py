
import turtle
import winsound

wn = turtle.Screen()
wn.title("Pong by Nick502")
wn.bgcolor("black")
wn.setup(width=800, height=600)
wn.tracer(0)

# Score
score_1 = 0
score_2 = 0


# Paddle Left
paddle_a = turtle.Turtle()
paddle_a.speed(0)
paddle_a.shape("square")
paddle_a.color("white")
paddle_a.shapesize(stretch_wid=5, stretch_len=1)
paddle_a.penup()
paddle_a.goto(-350, 0)


# Paddle Right
paddle_b = turtle.Turtle()
paddle_b.speed(0)
paddle_b.shape("square")
paddle_b.color("white")
paddle_b.shapesize(stretch_wid=5, stretch_len=1)
paddle_b.penup()
paddle_b.goto(350, 0)


# Ball
ball = turtle.Turtle()
ball.speed(0)
ball.shape("square")
ball.color("white")
ball.penup()
ball.goto(0, 0)

ball.dx = 0.06
ball.dy = 0.06


# Pen
pen = turtle.Turtle()
pen.speed(0)
pen.color("white")
pen.penup()
pen.hideturtle()
pen.goto(0, 250)
pen.write("Player 1: 0   Player 2: 0", align="center", font=("Courier", 24, "bold"))


# Function
def paddle_a_up():
    y = paddle_a.ycor()
    if y > 231:
        y = 250
    y += 20
    paddle_a.sety(y)


def paddle_a_down():
    y = paddle_a.ycor()
    if y < -231:
        y = -250
    y -= 20
    paddle_a.sety(y)


def paddle_b_up():
    y = paddle_b.ycor()
    if y > 231:
        y = 250
    y += 20
    paddle_b.sety(y)


def paddle_b_down():
    y = paddle_b.ycor()
    if y < -231:
        y = -250
    y -= 20
    paddle_b.sety(y)


# Keyboard Binding
wn.listen()
wn.onkeypress(paddle_a_up, "w")
wn.onkeypress(paddle_a_down, "s")
wn.onkeypress(paddle_b_up, "Up")
wn.onkeypress(paddle_b_down, "Down")


# ImageSets Game Loop
while True:
    wn.update()

    # Move the Ball
    ball.setx(ball.xcor() + ball.dx)
    ball.sety(ball.ycor() + ball.dy)

    # Border Checking
    if abs(ball.ycor()) > 290:
        ball.dy *= -1
        winsound.PlaySound("bounce.wav", winsound.SND_ASYNC)

    if ball.xcor() > 390:
        score_1 += 1
        pen.clear()
        pen.write("Player 1: {}   Player 2: {}".format(score_1, score_2), align="center",
                  font=("Courier", 24, "bold"))
        ball.dx *= -1
        ball.goto(0, 0)

    if ball.xcor() < -390:
        score_2 += 1
        pen.clear()
        pen.write("Player 1: {}   Player 2: {}".format(score_1, score_2), align="center",
                  font=("Courier", 24, "bold"))
        ball.dx *= -1
        ball.goto(0, 0)

    # Collisions
    if (ball.xcor() > 340 and ball.xcor() < 350) and paddle_b.ycor() + 50 > ball.ycor() > paddle_b.ycor() - 50:
        winsound.PlaySound("bounceR.wav", winsound.SND_ASYNC)
        ball.setx(340)
        ball.dx *= -1

    if (ball.xcor() < -340 and ball.xcor() > -350) and paddle_a.ycor() + 50 > ball.ycor() > paddle_a.ycor() - 50:
        winsound.PlaySound("bounceL.wav", winsound.SND_ASYNC)
        ball.setx(-340)
        ball.dx *= -1
