package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class FaseUm extends Fase {
    public FaseUm() {
        super();

        ImageIcon carregando = new ImageIcon("recursos\\fundo-placeholder.jpg");
        fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        this.inicializaInimigos();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
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
