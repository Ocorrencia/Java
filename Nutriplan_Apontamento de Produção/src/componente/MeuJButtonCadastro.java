/*  1:   */ package componente;
/*  2:   */ 
/*  3:   */ import java.awt.Font;
/*  4:   */ import java.awt.event.MouseEvent;
/*  5:   */ import java.awt.event.MouseListener;
/*  6:   */ import javax.swing.Icon;
/*  7:   */ import javax.swing.ImageIcon;
/*  8:   */ import javax.swing.JButton;
/*  9:   */ 
/* 10:   */ public class MeuJButtonCadastro
/* 11:   */   extends JButton
/* 12:   */   implements MouseListener
/* 13:   */ {
/* 14:   */   private Icon iconeMenor;
/* 15:   */   private Icon iconeMaior;
/* 16:   */   public boolean status;
/* 17:   */   
/* 18:   */   public MeuJButtonCadastro(String texto, Icon iconeMenor, Icon iconeMaior)
/* 19:   */   {
/* 20:17 */     setIcon(iconeMenor);
/* 21:18 */     this.iconeMenor = iconeMenor;
/* 22:19 */     this.iconeMaior = iconeMaior;
/* 23:20 */     setToolTipText(texto);
/* 24:21 */     setBorder(null);
/* 25:22 */     setFocusable(false);
/* 26:23 */     setBorderPainted(false);
/* 27:24 */     setContentAreaFilled(false);
/* 28:25 */     addMouseListener(this);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public MeuJButtonCadastro(String titulo, ImageIcon iconeMenor, String texto)
/* 32:   */   {
/* 33:29 */     setIcon(iconeMenor);
/* 34:30 */     setText(titulo.toUpperCase());
/* 35:31 */     setToolTipText(texto);
/* 36:32 */     setFont(new Font("Times New Roman", 1, 12));
/* 37:33 */     setFocusable(true);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public MeuJButtonCadastro(String texto, ImageIcon iconeMenor, ImageIcon iconeMaior, boolean status)
/* 41:   */   {
/* 42:37 */     setIcon(iconeMenor);
/* 43:38 */     this.iconeMenor = iconeMenor;
/* 44:39 */     this.iconeMaior = iconeMaior;
/* 45:40 */     this.status = status;
/* 46:41 */     setToolTipText(texto);
/* 47:42 */     setBorder(null);
/* 48:43 */     setFocusable(false);
/* 49:44 */     setBorderPainted(false);
/* 50:45 */     setContentAreaFilled(false);
/* 51:46 */     addMouseListener(this);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void mouseClicked(MouseEvent me)
/* 55:   */   {
/* 56:51 */     setIcon(this.iconeMenor);
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void mousePressed(MouseEvent me)
/* 60:   */   {
/* 61:56 */     if (this.status == true) {
/* 62:57 */       setIcon(this.iconeMaior);
/* 63:   */     }
/* 64:   */   }
/* 65:   */   
/* 66:   */   public void mouseReleased(MouseEvent me)
/* 67:   */   {
/* 68:63 */     if (this.status == true) {
/* 69:64 */       setIcon(this.iconeMaior);
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void mouseEntered(MouseEvent me)
/* 74:   */   {
/* 75:70 */     if (this.status == true) {
/* 76:71 */       setIcon(this.iconeMaior);
/* 77:   */     }
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void mouseExited(MouseEvent me)
/* 81:   */   {
/* 82:77 */     setIcon(this.iconeMenor);
/* 83:   */   }
/* 84:   */   
/* 85:   */   public void mudaStatus(boolean status)
/* 86:   */   {
/* 87:81 */     this.status = status;
/* 88:   */   }
/* 89:   */ }


/* Location:           C:\Users\Xaxa\Desktop\SISTEMASIND\Sindicato.jar
 * Qualified Name:     componente.MeuJButtonCadastro
 * JD-Core Version:    0.7.0.1
 */