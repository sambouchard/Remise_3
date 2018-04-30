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
    private int IdCaisson_gauche;
    private int IdCaisson_droite;
    private Piece[] liste_pieces = new Piece[3];

    public MontantCaissonVertical(Piece piece1,Piece piece2,Piece piece3,int idCaisson_gauche, int idCaisson_Droite) {
        this.IdCaisson_droite = idCaisson_Droite;
        this.IdCaisson_gauche = idCaisson_gauche;
        this.liste_pieces[1] = piece1;
        this.liste_pieces[1] = piece1;
        this.liste_pieces[1] = piece1;
    }
    
    
}
