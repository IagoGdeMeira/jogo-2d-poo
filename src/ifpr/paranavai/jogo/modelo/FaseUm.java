package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
        if(emJogo) {
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
        } else {
            ImageIcon fimDeJogo = new ImageIcon("recursos\\fim-de-jogo.png");
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRetangle();
        for(int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRetangle();
            
            if(formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
            }

            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for(int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRetangle();

                if(formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
        }
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
            Tiro tiro = tiros.get(i);
            if(tiros.get(i).getPosicaoEmX() > LARGURA_DA_JANELA || !tiro.getEhVisivel()) 
                tiros.remove(i);
            else
                tiros.get(i).atualizar();
        }

        for(int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = this.inimigos.get(i);
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                inimigos.remove(inimigo);
            else
                inimigo.atualizar();
        }

        this.verificarColisoes();

        repaint();
    }
}
