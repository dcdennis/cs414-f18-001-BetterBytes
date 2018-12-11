import pprint as pp
import random, sys, pickle, os

dir = os.path.dirname(__file__)

startingState = [['__', '__', '__', 'bR', 'bR', 'bR', 'bR', 'bR', '__', '__', '__'],
                 ['__', '__', '__', '__', '__', 'bR', '__', '__', '__', '__', '__'],
                 ['__', '__', '__', '__', '__', '__', '__', '__', '__', '__', '__'],
                 ['bR', '__', '__', '__', '__', 'wR', '__', '__', '__', '__', 'bR'],
                 ['bR', '__', '__', '__', 'wR', 'wR', 'wR', '__', '__', '__', 'bR'],
                 ['bR', 'bR', '__', 'wR', 'wR', 'wK', 'wR', 'wR', '__', 'bR', 'bR'],
                 ['bR', '__', '__', '__', 'wR', 'wR', 'wR', '__', '__', '__', 'bR'],
                 ['bR', '__', '__', '__', '__', 'wR', '__', '__', '__', '__', 'bR'],
                 ['__', '__', '__', '__', '__', '__', '__', '__', '__', '__', '__'],
                 ['__', '__', '__', '__', '__', 'bR', '__', '__', '__', '__', '__'],
                 ['__', '__', '__', 'bR', 'bR', 'bR', 'bR', 'bR', '__', '__', '__']]

# Add second list to return capture moves


startingState2 = [['__', '__', '__', 'bR', 'bR', 'bR', 'bR', 'bR', '__', '__', '__'],
                  ['__', '__', '__', '__', '__', 'bR', '__', '__', '__', '__', '__'],
                  ['__', '__', '__', '__', '__', '__', '__', '__', '__', '__', '__'],
                  ['bR', '__', '__', '__', '__', 'wR', '__', '__', '__', '__', 'bR'],
                  ['bR', '__', '__', '__', 'wR', 'wR', 'wR', '__', '__', '__', 'bR'],
                  ['bR', 'bR', '__', 'wR', 'wR', 'wK', 'wR', 'wR', '__', 'bR', 'bR'],
                  ['bR', '__', '__', '__', 'wR', 'wR', 'wR', 'bR', '__', '__', '__'],
                  ['bR', '__', '__', '__', '__', 'wR', '__', '__', '__', '__', 'bR'],
                  ['__', '__', '__', '__', '__', '__', '__', '__', '__', '__', '__'],
                  ['__', '__', '__', '__', '__', 'bR', '__', '__', '__', '__', '__'],
                  ['__', '__', '__', 'bR', 'bR', 'bR', 'bR', 'bR', '__', '__', '__']]


def useSavedQ():
    Q = loadQ()
    print(len(Q))
    # board,player = startingState,'white'
    board, player = readFile(dir + "/incomingGame.txt")
    pp.pprint(board)
    moves = validMoves(board, player)
    pp.pprint(moves)
    nextMove = pickMove(0.001, moves, board, Q, player)
    '''
    for keys in Q:
        if(keys[0] == str(startingState2)):
            print("found board")
            #if(keys[1] == ((7, 5), (8, 5))):
             #   print("found move")
              #  if(keys[2] == 'white'):
               #     print("FOUND: ", keys)
        else:
            print("actual key: ", str(keys[1]) + "VAl: ", str(Q.get(boardMoveTuple(keys[0],keys[1],keys[2]),-19.2)))
    '''

    print(nextMove)
    newBoard, _ = makeMove(board, nextMove)
    pp.pprint(newBoard)
    output(newBoard)


def saveQ(Q):
    with open(dir + '/savedQAggr.pkl', 'wb') as f:
        pickle.dump(Q, f)


def loadQ():
    with open(dir + '/savedQAggr.pkl', 'rb') as f:
        return pickle.load(f)


def output(board):
    f = open(dir + "/outgoingGame.txt", "w+")
    res = []
    for x in range(11):
        for y in range(11):
            res.append(str(x) + ":" + str(y) + ":" + board[x][y])
    # print(str(res))
    f.write(str(res))
    f.close()


# DONE
def pickMove(epsilon, validMoves, currentBoard, Q, color):
    if random.uniform(0, 1) < epsilon:
        bestMove = random.choice(validMoves)
        return bestMove
    else:
        # print("Not Random")
        maxVal = -999999999999
        maxMove = None
        for i in validMoves:
            # print("Q VAL: " + str(Q.get(boardMoveTuple(currentBoard,i,color),19.2)))
            if (Q.get(boardMoveTuple(currentBoard, i, color), -999999999999) >= maxVal):
                maxVal = Q.get(boardMoveTuple(currentBoard, i, color), -9999999999)
                maxMove = i
        # print("Move Val: "+ str(maxVal))
        return maxMove


def makeMove(oldBoard, move):
    pieceCaptured = 0
    board = copyBoard(oldBoard)
    startingLoc = move[0]
    endingLoc = move[1]

    board[endingLoc[0]][endingLoc[1]] = board[startingLoc[0]][startingLoc[1]]
    board[startingLoc[0]][startingLoc[1]] = "__"

    endX = endingLoc[0]
    endY = endingLoc[1]
    # Check and process captures
    # check right?
    # [0] = X Val
    # [1] = Y Val
    if (endingLoc[1] <= 8):
        captureX = endingLoc[0]
        captureY = endingLoc[1] + 1

        checkX = endingLoc[0]
        checkY = endingLoc[1] + 2
        if board[checkX][checkY][0] == board[endX][endY][0] \
                and board[captureX][captureY][0] != board[endX][endY][0] \
                and board[captureX][captureY] != '__':
            board[captureX][captureY] = "__"
            pieceCaptured += 1
    # check left?
    # [0] = X
    # [1] = Y
    if (endingLoc[1] >= 2):
        captureX = endingLoc[0]
        captureY = endingLoc[1] - 1

        checkX = endingLoc[0]
        checkY = endingLoc[1] - 2
        if board[checkX][checkY][0] == board[endX][endY][0] \
                and board[captureX][captureY][0] != board[endX][endY][0] \
                and board[captureX][captureY] != '__':
            board[captureX][captureY] = "__"
            pieceCaptured += 1
    # check down
    if (endingLoc[0] <= 8):
        captureX = endingLoc[0] + 1
        captureY = endingLoc[1]

        checkX = endingLoc[0] + 2
        checkY = endingLoc[1]
        if board[checkX][checkY][0] == board[endX][endY][0] \
                and board[captureX][captureY][0] != board[endX][endY][0] \
                and board[captureX][captureY] != '__':
            board[captureX][captureY] = "__"
            pieceCaptured += 1
    # check up
    if (endingLoc[0] >= 2):
        captureX = endingLoc[0] - 1
        captureY = endingLoc[1]

        checkX = endingLoc[0] - 2
        checkY = endingLoc[1]
        if board[checkX][checkY][0] == board[endX][endY][0] \
                and board[captureX][captureY][0] != board[endX][endY][0] \
                and board[captureX][captureY] != '__':
            board[captureX][captureY] = "__"
            pieceCaptured += 1
    return board, pieceCaptured


# done
def copyBoard(oldBoard):
    newBoard = []
    for x in range(11):
        newBoard.append([])
        for y in range(11):
            newBoard[x].append(oldBoard[x][y])
    return newBoard


# DONE
def boardMoveTuple(board, move, color):
    return (str(board), move, color)


# DONE
def readFile(path):
    file = open(path)
    board = createBoard(file.readline())
    player = file.readline()
    return (board, player)


# DONE
def createBoard(stateString):
    res = []
    for i in range(11):
        res.append([])
        for _ in range(11):
            res[i].append("__")
    # strips brackets
    stateString = stateString[1:-2]
    # splits into cells
    cells = stateString.split(",")
    # cell[0] is x, 1 is y, and 2 is the piece
    for cell in cells:
        cellData = cell.split(':')
        res[int(cellData[0])][int(cellData[1])] = cellData[2]
    return res


# DONE
def validMoves(board, player):
    # Due to the absolutely insane number of prints, dont turn this on unless you are doing single runs
    debug = False
    # res is a list of tuples, ((startx,starty),(endx,endy))
    res = []
    if debug:
        print("AI is playing: " + player)
    for x in range(11):
        for y in range(11):
            if debug:
                print("Checking for friendly piece at (" + str(x) + ", " + str(y) + ")")
            piece = board[x][y]
            if debug:
                print("Piece raw is: " + piece)
            # if it is the AIs piece
            if (piece[0] == player[0]):
                # if it is a rook
                if (piece[1] == "R"):
                    if debug:
                        print("\tPiece is a rook")
                    # up
                    for ymove in range(y + 1, 11):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                            print("\t\tContains: " + board[x][ymove])
                        if (board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not (((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x == 5 and ymove == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x, y), (x, ymove)))
                                if debug:
                                    pp.pprint(res)
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # down
                    for ymove in reversed(range(0, y)):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                            print("\t\tContains: " + board[x][ymove])
                        if (board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not (((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x == 5 and ymove == 5)):

                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x, y), (x, ymove)))
                                if debug:
                                    pp.pprint(res)
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # right
                    for xmove in range(x + 1, 11):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if (board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not (((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove == 5 and y == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x, y), (xmove, y)))
                                if debug:
                                    pp.pprint(res)
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # left
                    for xmove in reversed(range(0, x)):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if (board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not (((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove == 5 and y == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x, y), (xmove, y)))
                                if debug:
                                    pp.pprint(res)
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                # if it is a king
                if (piece[1] == "K"):
                    if debug:
                        print("\tPiece is a king")
                    # up
                    for ymove in range(y + 1, 11):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                        if debug:
                            print("\t\tContains: " + board[x][ymove])
                        if (board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x, y), (x, ymove)))

                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # down
                    for ymove in reversed(range(0, y)):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                        if debug:
                            print("\t\tContains: " + board[x][ymove])
                        if (board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x, y), (x, ymove)))
                            if debug:
                                pp.pprint(res)
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # right
                    for xmove in range(x + 1, 11):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if (board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x, y), (xmove, y)))
                            if debug:
                                pp.pprint(res)
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    # left
                    for xmove in reversed(range(0, x)):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if (board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x, y), (xmove, y)))
                            if debug:
                                pp.pprint(res)
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
    # res = [((7, 5), (7, 7))]
    res = captureValidMoves(res, copyBoard(board), player)
    return res


def captureValidMoves(res, board, player):
    captures = []
    for move in res:
        __, numCaps = makeMove(copyBoard(board), move)
        if (numCaps > 0):
            captures.append(move)
    if len(captures) > 0:
        return captures
    else:
        return res


# DONE
def result(board):
    if board[0][0] != "__" or board[10][0] != "__" or board[10][10] != "__" or board[0][10] != "__":
        return "white"
    kingFound = False
    for x in range(11):
        for y in range(11):
            if board[x][y] == "wK":
                kingFound = True
    if not kingFound:
        return "black"
    else:
        return "continue"


def trainQ(nRepitions, learningRate, epsilonDecayFactor, Q={}):
    # pp.pprint(Q)
    rho = .01
    epsilon = 1
    for i in range(nRepitions):
        if (i % 100 == 0):
            print("Status: " + str(i) + r" / " + str(nRepitions))

        currentState = startingState
        oldState = startingState
        oldMove = ((), ())
        turn = "white"
        numMoves = 0

        while result(currentState) == "continue" and numMoves < 2000:
            moves = validMoves(currentState, turn)
            if (moves == [] or moves == None):
                print("NO VALID MOVES")
                break
            pickedMove = pickMove(epsilon, moves, currentState, Q, turn)
            currentState, captures = makeMove(oldState, pickedMove)

            if (numMoves == 0):
                if (captures > 0):
                    # print("CAP: ", pickedMove)
                    Q[boardMoveTuple(oldState, pickedMove, turn)] = Q.get(boardMoveTuple(oldState, pickedMove, turn),
                                                                          0) + 10
                else:
                    Q[boardMoveTuple(oldState, pickedMove, turn)] = Q.get(boardMoveTuple(oldState, pickedMove, turn),
                                                                          0) + 0
            else:
                if (captures > 0):
                    # print("CAP: ", pickedMove)
                    Q[boardMoveTuple(oldState, oldMove, turn)] = Q.get(boardMoveTuple(oldState, oldMove, turn), 0) + 10
                else:
                    Q[boardMoveTuple(oldState, oldMove, turn)] = Q.get(boardMoveTuple(oldState, oldMove, turn), 0) + 0

            oldMove = pickedMove
            oldState = currentState

            if (result(currentState) == turn):
                Q[boardMoveTuple(oldState, pickedMove, turn)] = 100

            if (turn == "white"):
                turn = "black"
            else:
                turn = "white"
            numMoves += 1
        epsilon = epsilon - epsilon * epsilonDecayFactor
    return Q


debug = False
debug1 = False

if __name__ == '__main__':
    # saveQ(trainQ(1000,.05,.01))
    saveQ(trainQ(500, .05, 0.01, loadQ()))
    # useSavedQ()
    # output(startingState)
    # print("complete")

    '''

            if(turn == "white"):
                moves = validMoves(currentState,turn)
                if(moves == [] or moves == None):
                    print("NO VALID MOVES")
                    break
                pickedMove = pickMove(epsilon,moves,currentState,Q,turn)
                currentState, captures = makeMove(oldState,pickedMove)
                if (numMoves > 0):
                    if(captures > 0):
                        Q[boardMoveTuple(oldState,oldMove,turn)] = Q.get(boardMoveTuple(oldState,oldMove,turn),0) + (captures * 0.15) * (Q.get(boardMoveTuple(currentState,pickedMove,turn),0) -  Q.get((boardMoveTuple(oldState,oldMove,turn)),0))
                    else:
                        Q[boardMoveTuple(oldState,oldMove,turn)] = Q.get(boardMoveTuple(oldState,oldMove,turn),0) + rho * (Q.get(boardMoveTuple(currentState,pickedMove,turn),0) -  Q.get((boardMoveTuple(oldState,oldMove,turn)),0))
                oldState = currentState
                oldMove = pickedMove 
            if(turn == "black"):
                print()
            #if(captures > 0):
             #   print("Move: ", pickedMove)
              #  print("Capture: " , Q[boardMoveTuple(oldState,oldMove,turn)])
               # pp.pprint(currentState)

    '''