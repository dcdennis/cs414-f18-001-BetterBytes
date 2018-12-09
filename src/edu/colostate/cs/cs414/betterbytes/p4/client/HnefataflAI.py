import pprint as pp
import random, sys, pickle, os


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

def useSavedQ():
    Q = loadQ()
    board,player = readFile(os.path.dirname(__file__) + "/incomingGame.txt")
    moves = validMoves(board,player)
    nextMove = pickMove(0.001,moves,board,Q,player)
    newBoard,_ = makeMove(board,nextMove)
    output(newBoard)
         
def saveQ(Q):
    dir = os.path.dirname(__file__)
    with open(dir + '/savedQ.pkl','wb') as f:
        pickle.dump(Q,f)

def loadQ():
    dir = os.path.dirname(__file__)
    with open(dir + '/savedQ.pkl','rb') as f:
        return pickle.load(f)
 
def output(board):
    dir = os.path.dirname(__file__)
    f = open(dir + "/outgoingGame.txt","w+")
    res = []
    for x in range(11):
        for y in range(11):
            res.append(str(x) + ":" + str(y) + ":" + board[x][y])
    print(str(res))
    f.write(str(res))
    f.close()


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
    pieceCaptured = 0
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
        pieceCaptured =+ CheckCapture(board, endX, endY, 0,1,  endingLoc)
    #check up
    if(endingLoc[1] >= 2):
        pieceCaptured = + CheckCapture(board, endX, endY, 0, -1, endingLoc)
    #check left
    if(endingLoc[0] <= 8):
        pieceCaptured = + CheckCapture(board, endX, endY, 1, 0, endingLoc)
    #check right
    if(endingLoc[0] >= 2):
        pieceCaptured = + CheckCapture(board, endX, endY, -1, 0, endingLoc)
    return board, pieceCaptured


def CheckCapture(board, difX, difY, endingLoc):
    endX = endingLoc[0]
    endY = endingLoc[1]
    captureX = endingLoc[0] + difX
    captureY = endingLoc[1] + difY
    checkX = endingLoc[0] + difX * 2
    checkY = endingLoc[1] +difY * 2
    if board[checkX][checkY][0] == board[endX][endY][0] and not board[captureX][captureY][0] == board[endX][endY][0]:
        board[captureX][captureY] = "__"
        return 1
    return 0


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
        for _ in range(11):
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
    #Due to the absolutely insane number of prints, dont turn this on unless you are doing single runs
    debug = False
    #res is a list of tuples, ((startx,starty),(endx,endy)) 
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
            #if it is the AIs piece
            if(piece[0] == player[0]):
                #if it is a rook
                if(piece[1] == "R"):
                    if debug:
                        print("\tPiece is a rook")
                    #up
                    for ymove in range(y+1,11):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                            print("\t\tContains: " + board[x][ymove])
                        if(board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not(((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x==5 and ymove == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x,y),(x,ymove)))
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #down
                    for ymove in reversed(range(0,y)):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                            print("\t\tContains: " + board[x][ymove])
                        if(board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not(((x == 0 or x == 10) and (ymove == 0 or ymove == 10)) or (x==5 and ymove == 5)):
                                
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x,y),(x,ymove)))
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #right
                    for xmove in range(x+1,11):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if(board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not(((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove==5 and y == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x,y),(xmove,y)))
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #left
                    for xmove in reversed(range(0,x)):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if(board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            if not(((xmove == 0 or xmove == 10) and (y == 0 or y == 10)) or (xmove ==5 and y == 5)):
                                if debug:
                                    print("\t\t\tLocation is not forbiden")
                                res.append(((x,y),(xmove,y)))
                            else:
                                if debug:
                                    print("\t\t\tLocation is forbiden")
                                continue
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                #if it is a king
                if(piece[1] == "K"):
                    if debug:
                        print("\tPiece is a king")
                    #up
                    for ymove in range(y+1,11):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                        if debug:
                            print("\t\tContains: " + board[x][ymove])
                        if(board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x,y),(x,ymove)))
                            
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #down
                    for ymove in reversed(range(0,y)):
                        if debug:
                            print("\t\tChecking if " + str(x) + ", " + str(ymove) + " is occupied")
                        if debug:
                            print("\t\tContains: " + board[x][ymove])
                        if(board[x][ymove] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x,y),(x,ymove)))
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #right
                    for xmove in range(x+1,11):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if(board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x,y),(xmove,y)))
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
                    #left
                    for xmove in reversed(range(0,x)):
                        if debug:
                            print("\t\tChecking if " + str(xmove) + ", " + str(y) + " is occupied")
                            print("\t\tContains: " + board[xmove][y])
                        if(board[xmove][y] == "__"):
                            if debug:
                                print("\t\tLocation is empty")
                            res.append(((x,y),(xmove,y)))
                        else:
                            if debug:
                                print("\t\tLocation is occupied")
                            break
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


def trainQ(nRepitions,learningRate,eplsilonDecayFactor,Q = {}):
    #pp.pprint(Q)
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
            currentState, captures = makeMove(oldState,pickedMove)
            if(captures > 0):
                Q[boardMoveTuple(oldState,oldMove,turn)] = Q.get(boardMoveTuple(oldState,oldMove,turn),0) + (captures * 0.15) * (Q.get(boardMoveTuple(currentState,pickedMove,turn),0) -  Q.get((boardMoveTuple(oldState,oldMove,turn)),0))
            else:
                Q[boardMoveTuple(oldState,oldMove,turn)] = Q.get(boardMoveTuple(oldState,oldMove,turn),0) + rho * (Q.get(boardMoveTuple(currentState,pickedMove,turn),0) -  Q.get((boardMoveTuple(oldState,oldMove,turn)),0))
            oldMove = pickedMove
            if(result(currentState) == turn):
                Q[boardMoveTuple(oldState,pickedMove,turn)] = 1 
            if(turn == "white"):
                turn = "black"
            else:
                turn = "white"
    return Q

   

if __name__ == '__main__':
    #saveQ(trainQ(1000,.05,.01))
    #saveQ(trainQ(300,.05,.01,loadQ()))
    useSavedQ()
    #output(startingState)
    #print("complete")
