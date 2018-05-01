/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

/**
 *
 * @author SABOU350
 */
public class MontantCaissonVertical implements java.io.Serializable{
    private Caisson Caisson_gauche;
    private Caisson Caisson_droite;

    public Piece[] getListe_pieces() {
        return liste_pieces;
    }

    public void setListe_pieces(Piece[] liste_pieces) {
        this.liste_pieces = liste_pieces;
    }
    private Piece[] liste_pieces = new Piece[3];

    public MontantCaissonVertical(Piece piece1,Piece piece2,Piece piece3,Caisson Caisson_gauche, Caisson Caisson_Droite) {
        this.Caisson_droite = Caisson_Droite;
        this.Caisson_gauche = Caisson_gauche;
        this.liste_pieces[0] = piece1;
        this.liste_pieces[1] = piece2;
        this.liste_pieces[2] = piece3;
    }

    public Caisson getCaisson_gauche() {
        return Caisson_gauche;
    }

    public void setCaisson_gauche(Caisson Caisson_gauche) {
        this.Caisson_gauche = Caisson_gauche;
    }

    public Caisson getCaisson_droite() {
        return Caisson_droite;
    }

    public void setCaisson_droite(Caisson Caisson_droite) {
        this.Caisson_droite = Caisson_droite;
    }
    
    
}
