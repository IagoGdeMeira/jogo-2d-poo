package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Personagem extends ElementoGrafico {
    private int deslocamentoEmX, deslocamentoEmY;

    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100, POSICAO_INICIAL_EM_Y = 100;

    private ArrayList<Tiro> tiros;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        int caso = 0;

        if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W)
            caso = 1;
        else if(codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S)
            caso = 2;
        else if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A)
            caso = 3;
        else if(codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D)
            caso = 4;

        switch(caso) {
            case 1:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case 2:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case 3:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case 4:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        int caso = 0;

        if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W)
            caso = 1;
        else if(codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S)
            caso = 2;
        else if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A)
            caso = 3;
        else if(codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D)
            caso = 4;
        
        switch(caso) {
            case 1:
                this.deslocamentoEmY = 0;
                break;
            case 2:
                this.deslocamentoEmY = 0;
                break;
            case 3:
                this.deslocamentoEmX = 0;
                break;
            case 4:
                this.deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public void atirar () {
        int frenteNave = this.posicaoEmX + this.larguraImagem;
        int meioDaNave = this.posicaoEmY + (this.alturaImagem / 2);
        Tiro tiro = new Tiro(frenteNave, meioDaNave);
        this.tiros.add(tiro);
    }

    @Override
    protected void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\nave-principal.png");
        this.imagem = carregando.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    protected void atualizar() {
        this.posicaoEmX += deslocamentoEmX;
        this.posicaoEmY += deslocamentoEmY;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }
    
    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }
}
