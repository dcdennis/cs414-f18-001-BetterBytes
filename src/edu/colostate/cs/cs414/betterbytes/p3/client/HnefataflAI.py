import pprint as pp
import random, sys


startingState =    [['__', '__', '__', 'bR', 'bR', 'bR', 'bR', 'bR', '__', '__', '__'],
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

     
         

#DONE
def pickMove(epsilon,validMoves,currentBoard,Q,color):
    if random.uniform(0,1) < epsilon: 
        bestMove = random.choice(validMoves)
        return bestMove
    else:
        minVal = sys.maxsize
        minMove = None
        for i in validMoves:
            if(Q.get(boardMoveTuple(currentBoard,i,color),0) <= minVal):
                minVal = Q.get(boardMoveTuple(currentBoard,i,color),0)
                minMove = i
        return minMove

def makeMove(oldBoard,move):
    board = copyBoard(oldBoard)
    startingLoc = move[0]
    endingLoc = move[1]

    board[endingLoc[0]][endingLoc[1]] = board[startingLoc[0]][startingLoc[1]]
    board[startingLoc[0]][startingLoc[1]] = "__"
    
    endX = endingLoc[0]
    endY = endingLoc[1]
    #Check and process captures
    #check down
    # [0] = X Val
    # [1] = Y Val
    if(endingLoc[1] <= 8):
        captureX = endingLoc[0]
        captureY = endingLoc[1]+1

        checkX = endingLoc[0]
        checkY = endingLoc[1]+2
        if board[checkX][checkY][0] == board[endX][endY][0] and not board[captureX][captureY][0] == board[endX][endY][0]:
            board[captureX][captureY] = "__"
    #check up
    if(endingLoc[1] >= 2):
        captureLoc = (endingLoc[0],endingLoc[1]-1)
        checkLoc   = (endingLoc[0],endingLoc[1]-2)
        if board[checkLoc[0]][checkLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0] and not board[captureLoc[0]][captureLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0]:
            board[captureLoc[0]][captureLoc[1]] = "__"
    #check left
    if(endingLoc[0] <= 8):
        captureLoc = (endingLoc[0]+1,endingLoc[1])
        checkLoc = (endingLoc[0]+2,endingLoc[1])
        if board[checkLoc[0]][checkLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0] and not board[captureLoc[0]][captureLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0]:
            board[captureLoc[0]][captureLoc[1]] = "__"
    #check right
    if(endingLoc[0] >= 2):
        captureLoc = (endingLoc[0]-1,endingLoc[1])
        checkLoc = (endingLoc[0]-2,endingLoc[1])
        if board[checkLoc[0]][checkLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0] and not board[captureLoc[0]][captureLoc[1]][0] == board[endingLoc[0]][endingLoc[1]][0]:
            board[captureLoc[0]][captureLoc[1]] = "__"
    return board

#done
def copyBoard(oldBoard):
    newBoard = []
    for x in range(11):
        newBoard.append([])
        for y in range(11):
            newBoard[x].append(oldBoard[x][y])
    return newBoard
    
#DONE
def boardMoveTuple(board,move,color):
    return (str(board),move,color)

#DONE
def readFile(path):
    file = open(path)
    board = createBoard(file.readline())
    player = file.readline()
    #pp.pprint(board)
    #pp.pprint(player)
    return (board,player)

#DONE
def createBoard(stateString):
    res = []
    for i in range(11):
        res.append([])
        for j in range(11):
            res[i].append("__")
    #strips brackets
    stateString = stateString[1:-2]
    #splits into cells
    cells = stateString.split(",")
    #cell[0] is x, 1 is y, and 2 is the piece
    for cell in cells:
        cellData = cell.split(':')
        res[int (cellData[0])][int(cellData[1])] = cellData[2]
    return res   

#DONE
def validMoves(board,player):
    #res is a list of tuples, ((startx,starty),(endx,endy))
    res = []
    for x in range(11):
        for y in range(11):
            piece = board[x][y]
            #if it is the AIs piece
            if(piece[0] == player[0]):
                #if it is a rook
                if(piece[1] == "R"):
                    #up
                    for ymove in range(y,11):
                        if(board[x][ymove] == "__"):
                            if not(((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x==5 and ymove == 5)):
                                res.append(((x,y),(x,ymove)))
                        else:
                            continue
                    #down
                    for ymove in reversed(range(0,y)):
                        if(board[x][ymove] == "__"):
                            if not(((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x==5 and ymove == 5)):
                                res.append(((x,y),(x,ymove)))
                        else:
                            continue
                    #right
                    for xmove in range(x,11):
                        if(board[xmove][y] == "__"):
                            if not(((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove==5 and y == 5)):
                                res.append(((x,y),(xmove,y)))
                        else:
                            continue
                    #left
                    for xmove in reversed(range(0,x)):
                        if(board[xmove][y] == "__"):
                            if not(((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove ==5 and y == 5)):
                                res.append(((x,y),(xmove,y)))
                        else:
                            continue
                #if it is a king
                if(piece[1] == "K"):
                    #up
                    for ymove in range(y,11):
                        if(board[x][ymove] == "__"):
                            res.append(((x,y),(x,ymove)))
                        else:
                            continue
                    #down
                    for ymove in reversed(range(0,y)):
                        if(board[x][ymove] == "__"):
                            res.append(((x,y),(x,ymove)))
                        else:
                            continue
                    #right
                    for xmove in range(x,11):
                        if(board[xmove][y] == "__"):
                            res.append(((x,y),(xmove,y)))
                        else:
                            continue
                    #left
                    for xmove in reversed(range(0,x)):
                        if(board[xmove][y] == "__"):
                            res.append(((x,y),(xmove,y)))
                        else:
                            continue
    return res

#DONE
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


def trainQ(nRepitions,learningRate,eplsilonDecayFactor):
    # Q maps (str(board),move,color) -> score
    Q = {}
    rho = .01
    epsilon = 1
    for _ in range(nRepitions):
        currentState = startingState
        oldState = []
        oldMove = ()
        turn = "black"
        while result(currentState) == "continue":
            moves = validMoves(currentState,turn)
            if(moves == [] or moves == None):
                print("NO VALID MOVES")
                break
            pickedMove = pickMove(epsilon,moves,currentState,Q,turn)
            oldState = currentState
            currentState = makeMove(oldState,pickedMove)
            Q[boardMoveTuple(oldState,oldMove,turn)] = Q.get(boardMoveTuple(oldState,oldMove,turn),0) + rho * (Q.get(boardMoveTuple(currentState,pickedMove,turn),0) -  Q.get((boardMoveTuple(oldState,oldMove,turn)),0))
            oldMove = pickedMove
            if(turn == "white"):
                turn = "black"
            else:
                turn = "white"
   
        Q[boardMoveTuple(oldState,pickedMove,turn)] = 1 
        pp.pprint(currentState)
        print("\n\n\n")

    return Q

   

if __name__ == '__main__':
    Q = trainQ(10,.05,.01)
    #pp.pprint(Q)