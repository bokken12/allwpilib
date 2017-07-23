import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * 
 */

/**
 * @author joelmanning
 *
 */
public abstract class OneSwing extends JFrame {
	public <T extends JComponent> T construct(T component, Object... args){
		for(Object obj: args){
			if(obj instanceof Function){
				obj = ((Function) obj).apply(component);
			}
			if(obj instanceof Component){
				component.add((Component) obj);
			} else if(obj instanceof LayoutManager){
				component.setLayout((LayoutManager) obj);
			} else if(obj instanceof Color){
				component.setBackground((Color) obj);
			} else if(obj instanceof Consumer){
				((Consumer<T>) obj).accept(component);
			} else if(obj instanceof String){
				try {
					getClass().getDeclaredField((String) obj).set(this, component);
				} catch(IllegalArgumentException e) {
					e.printStackTrace();
				} catch(IllegalAccessException e) {
					e.printStackTrace();
				} catch(NoSuchFieldException e) {
					e.printStackTrace();
				} catch(SecurityException e) {
					e.printStackTrace();
				}
			} else if(obj instanceof AncestorListener){
				component.addAncestorListener((AncestorListener) obj);
			} else if(obj instanceof ComponentListener){
				component.addComponentListener((ComponentListener) obj);
			} else if(obj instanceof ContainerListener){
				component.addContainerListener((ContainerListener) obj);
			} else if(obj instanceof FocusListener){
				component.addFocusListener((FocusListener) obj);
			} else if(obj instanceof HierarchyBoundsListener){
				component.addHierarchyBoundsListener((HierarchyBoundsListener) obj);
			} else if(obj instanceof HierarchyListener){
				component.addHierarchyListener((HierarchyListener) obj);
			} else if(obj instanceof InputMethodListener){
				component.addInputMethodListener((InputMethodListener) obj);
			} else if(obj instanceof KeyListener){
				component.addKeyListener((KeyListener) obj);
			} else if(obj instanceof MouseListener){
				component.addMouseListener((MouseListener) obj);
			} else if(obj instanceof MouseMotionListener){
				component.addMouseMotionListener((MouseMotionListener) obj);
			} else if(obj instanceof MouseWheelListener){
				component.addMouseWheelListener((MouseWheelListener) obj);
			} else if(obj instanceof PropertyChangeListener){
				component.addPropertyChangeListener((PropertyChangeListener) obj);
			} else if(obj instanceof VetoableChangeListener){
				component.addVetoableChangeListener((VetoableChangeListener) obj);
			}
		}
		return component;
	}
	public static AncestorListener onAncestorAdded(Consumer<AncestorEvent> consumer){
		return new AncestorListener(){
			@Override
			public void ancestorAdded(AncestorEvent event) {
				consumer.accept(event);
			}
			@Override
			public void ancestorRemoved(AncestorEvent event) {}
			@Override
			public void ancestorMoved(AncestorEvent event) {}
		};
	}
	public static AncestorListener onAncestorMoved(Consumer<AncestorEvent> consumer){
		return new AncestorListener(){
			@Override
			public void ancestorAdded(AncestorEvent event) {}
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				consumer.accept(event);
			}
			@Override
			public void ancestorMoved(AncestorEvent event) {}
		};
	}
	public static AncestorListener onAncestorRemoved(Consumer<AncestorEvent> consumer){
		return new AncestorListener(){
			@Override
			public void ancestorAdded(AncestorEvent event) {}
			@Override
			public void ancestorRemoved(AncestorEvent event) {}
			@Override
			public void ancestorMoved(AncestorEvent event) {
				consumer.accept(event);
			}
		};
	}
	public static ComponentListener onComponentHidden(Consumer<ComponentEvent> consumer){
		return new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static ComponentListener onComponentMoved(Consumer<ComponentEvent> consumer){
		return new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {
				consumer.accept(e);
			}
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
		};
	}
	public static ComponentListener onComponentResized(Consumer<ComponentEvent> consumer){
		return new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {
				consumer.accept(e);
			}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
		};
	}
	public static ComponentListener onComponentShown(Consumer<ComponentEvent> consumer){
		return new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentShown(ComponentEvent e) {
				consumer.accept(e);
			}
			@Override
			public void componentHidden(ComponentEvent e) {}
		};
	}
	public static ContainerListener onComponentAdded(Consumer<ContainerEvent> consumer){
		return new ContainerListener(){
			@Override
			public void componentAdded(ContainerEvent e) {
				consumer.accept(e);
			}
			@Override
			public void componentRemoved(ContainerEvent e) {}
		};
	}
	public static ContainerListener onComponentRemoved(Consumer<ContainerEvent> consumer){
		return new ContainerListener(){
			@Override
			public void componentAdded(ContainerEvent e) {}
			@Override
			public void componentRemoved(ContainerEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static FocusListener onFocusGained(Consumer<FocusEvent> consumer){
		return new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				consumer.accept(e);
			}
			@Override
			public void focusLost(FocusEvent e) {}
		};
	}
	public static FocusListener onFocusLost(Consumer<FocusEvent> consumer){
		return new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static HierarchyBoundsListener onHierarchalAncestorMoved(Consumer<HierarchyEvent> consumer){
		return new HierarchyBoundsListener(){
			@Override
			public void ancestorMoved(HierarchyEvent e) {
				consumer.accept(e);
			}
			@Override
			public void ancestorResized(HierarchyEvent e) {}
		};
	}
	public static HierarchyBoundsListener onHierarchalAncestorResized(Consumer<HierarchyEvent> consumer){
		return new HierarchyBoundsListener(){
			@Override
			public void ancestorMoved(HierarchyEvent e) {}
			@Override
			public void ancestorResized(HierarchyEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static HierarchyListener onHierarchyChanged(Consumer<HierarchyEvent> consumer){
		return new HierarchyListener(){
			@Override
			public void hierarchyChanged(HierarchyEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static InputMethodListener onCaretPositionChanged(Consumer<InputMethodEvent> consumer){
		return new InputMethodListener(){
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {}
			@Override
			public void caretPositionChanged(InputMethodEvent event) {
				consumer.accept(event);
			}
		};
	}
	public static InputMethodListener onInputMethodTextChanged(Consumer<InputMethodEvent> consumer){
		return new InputMethodListener(){
			@Override
			public void inputMethodTextChanged(InputMethodEvent event) {
				consumer.accept(event);
			}
			@Override
			public void caretPositionChanged(InputMethodEvent event) {}
		};
	}
	public static KeyListener onKeyPressed(Consumer<KeyEvent> consumer){
		return new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				consumer.accept(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		};
	}
	public static KeyListener onKeyReleased(Consumer<KeyEvent> consumer){
		return new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static KeyListener onKeyTyped(Consumer<KeyEvent> consumer){
		return new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				consumer.accept(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		};
	}
	public static MouseListener onMouseClicked(Consumer<MouseEvent> consumer){
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				consumer.accept(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}
	public static MouseListener onMouseEntered(Consumer<MouseEvent> consumer){
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				consumer.accept(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}
	public static MouseListener onMouseExited(Consumer<MouseEvent> consumer){
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static MouseListener onMousePressed(Consumer<MouseEvent> consumer){
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				consumer.accept(e);
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}
	public static MouseListener onMouseReleased(Consumer<MouseEvent> consumer){
		return new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				consumer.accept(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}
	public static MouseMotionListener onMouseDragged(Consumer<MouseEvent> consumer){
		return new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				consumer.accept(e);
			}
			@Override
			public void mouseMoved(MouseEvent e) {}
		};
	}
	public static MouseMotionListener onMouseMoved(Consumer<MouseEvent> consumer){
		return new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {}
			@Override
			public void mouseMoved(MouseEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static MouseWheelListener onMouseWheelMoved(Consumer<MouseWheelEvent> consumer){
		return new MouseWheelListener(){
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				consumer.accept(e);
			}
		};
	}
	public static PropertyChangeListener onPropertyChange(Consumer<PropertyChangeEvent> consumer){
		return new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				consumer.accept(evt);
			}
		};
	}
	public static VetoableChangeListener onVetoableChange(Consumer<PropertyChangeEvent> consumer){
		return new VetoableChangeListener(){
			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				consumer.accept(evt);
			}
		};
	}
	public static <T extends JComponent> Consumer<T> initializeWith(Consumer<T> consumer){
		return consumer;
	}
	public static Consumer<JComponent> setDimensionsStrict(int width, int height){
		return new Consumer<JComponent>(){
			@Override
			public void accept(JComponent t) {
				Dimension dim = new Dimension(width, height);
				t.setPreferredSize(dim);
				t.setMinimumSize(dim);
				t.setMaximumSize(dim);
			}
		};
	}
	public static <T extends JComponent> Function<T, Object> initializeWith(Function<T, Object> function){
		return  function;
	}
}
