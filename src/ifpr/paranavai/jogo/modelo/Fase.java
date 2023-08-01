package ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

import java.lang.Math;

public class Fase extends JPanel implements ActionListener, KeyListener {
    private Image fundo;
    private Personagem personagem;
    private Timer timer;
    
    private static final int DELAY = 5;
    private static final int LARGURA_DA_JANELA = 938;
    private static final int QNTD_DE_INIMIGOS = 40;

    private ArrayList<Inimigo> inimigos;


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

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

        ArrayList<Tiro> tiros = personagem.getTiros();
        for(Tiro tiro : tiros) {
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }

        for(Inimigo inimigo : inimigos) {
            inimigo.carregar();
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
        }

        g.dispose();
    }

    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();
        for(int i = 0; i < QNTD_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();
        for(int i = 0; i < tiros.size(); i++) {
            if(tiros.get(i).getPosicaoEmX() > LARGURA_DA_JANELA) 
                tiros.remove(i);
            else
                tiros.get(i).atualizar();
        }

        for(int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = this.inimigos.get(i);
            if (inimigo.getPosicaoEmX() < 0)
                inimigos.remove(inimigo);
            else
                inimigo.atualizar();
        }

        repaint();
    }
}
