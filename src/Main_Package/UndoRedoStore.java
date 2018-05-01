/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesc
 */
public class UndoRedoStore {
    private static final UndoRedoStore instance = new UndoRedoStore();
    private static List<Etagere> stack = new ArrayList<Etagere>();
    private static int stackPointer = -1;
    
    private UndoRedoStore(){}
    
    @Override
    public String toString() {
        String out = new String();
        System.out.println("Stack pointer at position: " + stackPointer);
        System.out.println("Max index: " + (stack.size() - 1));
        return out;
    }

    public static UndoRedoStore getInstance() {
        return instance;
    }
    
    public static void addMutation(Etagere e) {
        Etagere clonedEtagere = null;
        try {
            clonedEtagere = cloneEtagere(e);
        } catch (IOException ex) {
            Logger.getLogger(UndoRedoStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clonedEtagere != null) {
            if (stackPointer !=  stack.size() - 1) {
                stack = stack.subList(0, stackPointer);
            }
            stack.add(clonedEtagere);
            stackPointer = stack.size() - 1;
        }
    }
    
    public static boolean canUndo() {
        return (stackPointer  != 0);
    }
    
    public static boolean canRedo() {
        return (stackPointer !=  (stack.size() - 1) );
    }
    
    public static Etagere undo() {
        stackPointer -= 1;
        return stack.get(stackPointer);
    }
    
    public static Etagere redo() {
        stackPointer += 1;
        return stack.get(stackPointer);
    }
    
    public static Etagere cloneEtagere(Etagere e) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutputStream oStream = new ObjectOutputStream(bStream);
        oStream.writeObject(e);
        oStream.flush();
        oStream.close();
        bStream.close();
        byte[] byteObject = bStream.toByteArray();
        ByteArrayInputStream iStream = new ByteArrayInputStream(byteObject);
        try {
            Etagere outEtagere = (Etagere) new ObjectInputStream(iStream).readObject();
            return outEtagere;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UndoRedoStore.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
