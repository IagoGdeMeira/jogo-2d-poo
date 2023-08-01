package ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

public abstract class Fase extends JPanel implements ActionListener, KeyListener {
    protected Image fundo;
    protected Personagem personagem;
    protected Timer timer;
    
    public static final int DELAY = 5;
    public static final int LARGURA_DA_JANELA = 938;
    public static final int QNTD_DE_INIMIGOS = 40;

    protected ArrayList<Inimigo> inimigos;

    protected boolean emJogo = true;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\fundo-placeholder.jpg");
        fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        this.inicializaInimigos();

        addKeyListener(this);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public abstract void paint(Graphics g);
    public abstract void inicializaInimigos();
    public abstract void verificarColisoes();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
