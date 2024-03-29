import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/
 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	
 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }
 
        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}

	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
 
	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
 
        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

		int landingX = (e.getX()/75);//gets coordinates
		int landingY = (e.getY()/75);//gets coordinates
		int xMovement = Math.abs((e.getX()/75)-startX);
		int yMovement = Math.abs((e.getY()/75)-startY);

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/

		if(pieceName.contains("King")){
			Boolean intheWay = false;
			int distance = Math.abs(startX-landingX);
			if(((landingX < 0)||(landingX > 7)||((landingY < 0)||(landingY > 7)))){
				validMove = false;
			}
			else{
				if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) == 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						int xmovement = Math.abs(startX-landingX);
						if(startX-landingX > 0){
							for(int i=0;i < xmovement;i++){
								if(piecePresent(initialX-(i*75), e.getY())){//checks to see if a friendly piece is in the way
									intheWay = true;//blocks king from moving onto a friendly piece
									break;
								}
								else{
									intheWay = false;//tells king there is nothing in the way
								}
							}
						}
						else{
							for(int i=0; i < xmovement;i++){//controls movement in x direction and checks if there is a friendly piece in the way
								if(piecePresent(initialX+(i*75), e.getY())){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
					}
					else{
						int ymovement = Math.abs(startY-landingY);
						if(startY-landingY > 0){
							for(int i=0; i < ymovement;i++){//controls movement in the y direction and checks to see if there is a friendly piece in the way
								if(piecePresent(e.getX(),initialY+(i*75))){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
						else{
							for(int i=0; i < ymovement;i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
					}

					if(intheWay){
						validMove = false;// if there is something in the way, the king cannot move to desired square
					}
					else if((xMovement > 1)||(yMovement > 1)){
						validMove = false;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){//checks if the piece present is the white opponent's
									validMove = true;//allows king to capture the white opponent's piece
								}
								else{
									validMove = false;//does not allow king to take its own piece
								}
							}
							else{
								if(checkBlackOponent(e.getX(), e.getY())){//checks if the piece present is the black opponent's
									validMove = true;//allows king to capture the black opponent's piece
								}
								else{
									validMove = false;//does not allow king to take its own piece
								}
							}
						}
						else{
							validMove = true;
						}
					}
				}
				else{
					validMove = true; //allows king to move when not blocked by friendly piece
					if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
						if((startX-landingX < 0)&&(startY-landingY < 0)){
							for(int i=0; i <distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX < 0)&&(startY-landingY >0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY > 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY < 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
									intheWay = true;
								}
							}
						}
						if (intheWay) {
							validMove = false;
						}//allows king to move diagonally
						else if((xMovement > 1)&&(yMovement > 1)){
							validMove = false;
						}
						else{
							if(piecePresent(e.getX(), (e.getY()))){
								if(pieceName.contains("White")){
									if(checkWhiteOponent(e.getX(), e.getY())){
										validMove = true;
									}//allows black piece to take opponents white piece
									else{
										validMove = false;
									}//blocks king from taking its own piece
								}
								else{
									if(checkBlackOponent(e.getX(), e.getY())){
										validMove = true;
									}//allows white king to take opponents black piece
									else{
										validMove = false;
									}//blocks white king from taking its own piece
								}
							}
							else{
								validMove = true;
							}
						}
					}
					else{
						validMove = false;
					}
				}
			}
		}

		if(pieceName.contains("Queen")){
			Boolean intheWay = false;
			int distance = Math.abs(startX-landingX);
			if(((landingX < 0)||(landingX > 7)||((landingY < 0)||(landingY > 7)))){
				validMove = false;
			}
			else{
				if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) == 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						int xmovement = Math.abs(startX-landingX);
						if(startX-landingX > 0){
							for(int i=0;i < xmovement;i++){
								if(piecePresent(initialX-(i*75), e.getY())){//checks to see if a friendly piece is in the way
									intheWay = true;//blocks Queen from moving onto or past friendly piece if it is in the way
									break;
								}
								else{
									intheWay = false;//tells Queen there is nothing in the way
								}
							}
						}
						else{
							for(int i=0; i < xmovement;i++){//controls movement in x direction and constantly checks if there is a friendly piece in the way
								if(piecePresent(initialX+(i*75), e.getY())){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
					}
					else{
						int ymovement = Math.abs(startY-landingY);
						if(startY-landingY > 0){
							for(int i=0; i < ymovement;i++){//controls movement in the y direction and constantly checks to see if there is a friendly piece in the way
								if(piecePresent(e.getX(),initialY+(i*75))){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
						else{
							for(int i=0; i < ymovement;i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheWay = true;
									break;
								}
								else{
									intheWay = false;
								}
							}
						}
					}

					if(intheWay){
						validMove = false;// if there is something in the way, the Queen cannot move to desired square
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){//checks if the piece present is the white opponent's
									validMove = true;//allows Queen to capture the white opponent's piece
								}
								else{
									validMove = false;//does not allow Queen to take its own piece
								}
							}
							else{
								if(checkBlackOponent(e.getX(), e.getY())){//checks if the piece present is the black opponent's
									validMove = true;//allows Queen to capture the black opponent's piece
								}
								else{
									validMove = false;//does not allow Queen to take its own piece
								}
							}
						}
						else{
							validMove = true;
						}
					}
				}
				else{
					validMove = true; //allows Queen to move when not blocked by friendly piece
					if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
						if((startX-landingX < 0)&&(startY-landingY < 0)){
							for(int i=0; i <distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX < 0)&&(startY-landingY >0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY > 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
									intheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY < 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
									intheWay = true;
								}
							}
						}
						if (intheWay) {
							validMove = false;
						}//allows Queen to move diagonally
						else{
							if(piecePresent(e.getX(), (e.getY()))){
								if(pieceName.contains("White")){
									if(checkWhiteOponent(e.getX(), e.getY())){
										validMove = true;
									}//allows black piece to take opponents white piece
									else{
										validMove = false;
									}//blocks Queen from taking its own piece
								}
								else{
									if(checkBlackOponent(e.getX(), e.getY())){
										validMove = true;
									}//allows white Queen to take opponents black piece
									else{
										validMove = false;
									}//blocks white Queen from taking its own piece
								}
							}
							else{
								validMove = true;
							}
						}
					}
					else{
						validMove = false;
					}
				}
			}
		}

		if(pieceName.contains("Rook")){
			Boolean intheway = false;// used to check if a friendly piece is in the way
			if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
				validMove = false;// blocks rook from moving on the first turn
			}
			else{
				if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) == 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
					if(Math.abs(startX-landingX)!=0){
						int xmovement = Math.abs(startX-landingX);
						if(startX-landingX > 0){
							for(int i=0;i < xmovement;i++){
								if(piecePresent(initialX-(i*75), e.getY())){//checks to see if a friendly piece is in the way
									intheway = true;//blocks rook from moving onto or past friendly piece if it is in the way
									break;
								}
								else{
									intheway = false;//tells rook there is nothing in the way
								}
							}
						}
						else{
							for(int i=0; i < xmovement;i++){//controls movement in x direction and constantly checks if there is a friendly piece in the way
								if(piecePresent(initialX+(i*75), e.getY())){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
					}
					else{
						int ymovement = Math.abs(startY-landingY);
						if(startY-landingY > 0){
							for(int i=0; i < ymovement;i++){//controls movement in the y direction and constantly checks to see if there is a friendly piece in the way
								if(piecePresent(e.getX(),initialY+(i*75))){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for(int i=0; i < ymovement;i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
					}

					if(intheway){
						validMove = false;// if there is something in the way, the rook cannot move to desired square
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){//checks if the piece present is the white opponent's
									validMove = true;//allows rook to capture the white opponent's piece
								}
								else{
									validMove = false;//does not allow rook to take its own piece
								}
							}
							else{
								if(checkBlackOponent(e.getX(), e.getY())){//checks if the piece present is the black opponent's
									validMove = true;//allows rook to capture the black opponent's piece
								}
								else{
									validMove = false;//does not allow rook to take its own piece
								}
							}
						}
						else{
							validMove = true;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}

		if(pieceName.contains("Bishop")) {//bishop code
			Boolean inTheWay = false;
			int distance = Math.abs(startX-landingX);
			if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
				validMove = false;
			}// bishop is not allowed to move from starting position if friendly pawn is in the way
			else{
				validMove = true; //allows bishop to move when not blocked by friendly piece
				if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
					if((startX-landingX < 0)&&(startY-landingY < 0)){
						for(int i=0; i <distance;i++){
							if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX < 0)&&(startY-landingY >0)){
						for(int i=0; i < distance;i++){
							if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY > 0)){
						for(int i=0; i < distance;i++){
							if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
								inTheWay = true;
							}
						}
					}
					else if((startX-landingX > 0)&&(startY-landingY < 0)){
						for(int i=0; i < distance;i++){
							if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
								inTheWay = true;
							}
						}
					}
					if (inTheWay) {
						validMove = false;
					}//allows bishop to move diagonally
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true;
								}//allows black piece to take opponents white piece
								else{
									validMove = false;
								}//blocks bishop from taking its own piece
							}
							else{
								if(checkBlackOponent(e.getX(), e.getY())){
									validMove = true;
								}//allows white bishop to take opponents black piece
								else{
									validMove = false;
								}//blocks white bishop from taking its own piece
							}
						}
						else{
							validMove = true;
						}
					}
					}
				else{
					validMove = false;
				}
			}
		}

		if(pieceName.contains("Knight")){
			if(((xMovement == 1)&&(yMovement ==2)||(xMovement==2)&&(yMovement==1))){
				if(!piecePresent(e.getX(), e.getY())){
					validMove = true;//allows Knight to move if no friendly piece is blocking its movement to a specific square
				}
				else{
					if(pieceName.contains("White")){
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;//allows black knight to capture
						}
					}
					else{
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true;//allows white knight to capture
						}
					}
				}
			}
		}

		else if(pieceName.equals("BlackPawn")){
			if(startY == 6){// The pawn's first move

				/* if the pawn is making its first movement
				the pawn can either move one square or two squares in the Y direction
				as long as its moving forward and not backwards. there shouldnt be any movement in the x direction
				 */
				if(((yMovement==1)||(yMovement == 2))&&(startY > landingY)&&(xMovement ==0)){
					if(yMovement == 2){
						if(!piecePresent(e.getX(), e.getY())&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;
						}
					}
					else{
						if(!piecePresent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}
				/* This code refers to the rule where a pawn shouldnt move diagonally unless taking a piece. */
				else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
					if(piecePresent(e.getX(), e.getY())){
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}
			}
			else{// the pawn's making a move that isn't it's first
				if(((yMovement==1))&&(startY > landingY)&&(xMovement ==0)){
					if(!piecePresent(e.getX(), e.getY())){
						validMove = true;
					}
				}
				else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
					if(piecePresent(e.getX(), e.getY())){
						if(checkBlackOponent(e.getX(), e.getY())){
							validMove = true;
						}
					}
				}
			}
		}

		if(pieceName.equals("WhitePawn")){
			if(startY == 1)
			{
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
				{
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;					
						}
						else{
							validMove = false;
						}							
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;					
						}	
						else{
							validMove = false;
						}													
					}
				}
				else{
					validMove = false;					
				}
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;
				if((startX-1 >=0)||(startX +1 <=7))
				{
					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								success = true;
							}
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}
		if(!validMove){		
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png"; 
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);			
		}
		else{
			if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);			
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	            	
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);									
			}
		}
    }
 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}


