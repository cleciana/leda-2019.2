package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      RBNode<T> node = (RBNode<T>) root;
      int size = 0;

      while (!node.isEmpty()) {
         if (node.getColour() == Colour.BLACK)
            size += 1;
         node = (RBNode<T>) node.getRight();
      }
      return size;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed by
    * the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must be
    * BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildren((RBNode<T>) root);
   }

   private boolean verifyChildren(RBNode<T> node) {
      boolean aux = true;

      if (!node.isEmpty()) {
         if (node.getColour() == Colour.RED) {
            aux = !(((RBNode<T>) node.getLeft()).getColour() == Colour.RED)
                  || ((RBNode<T>) node.getRight()).getColour() == Colour.RED;
         }
         aux = verifyChildren((RBNode<T>) node.getLeft()) && verifyChildren((RBNode<T>) node.getRight());
      }
      return aux;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight((BSTNode<T>) root.getLeft(), 0) == verifyBlackHeight((BSTNode<T>) root.getRight(), 0);
   }

   private int verifyBlackHeight(BSTNode<T> node, int i) {
      if (node != null && !node.isEmpty()) {
         if (((RBNode<T>) node).getColour() == Colour.BLACK) {
            i += 1;
         }
         return Math.max(verifyBlackHeight((BSTNode<T>) node.getLeft(), i),
               verifyBlackHeight((BSTNode<T>) node.getRight(), i));
      }
      return i + 1;
   }

   @Override
   public void insert(T value) {
      if (value != null)
         insert((RBNode<T>) root, value, new RBNode<T>());
   }

   private void insert(RBNode<T> node, T value, RBNode<T> parent) {
      if (node.isEmpty()) {
         node.setData(value);
         node.setColour(Colour.RED);
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());
         node.setParent(parent);

      } else if (value.compareTo(node.getData()) > 0) {
         insert((RBNode<T>) node.getRight(), value, node);
      } else {
         insert((RBNode<T>) node.getLeft(), value, node);
      }
      fixUpCase1(node);
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[size()];

      if (!this.isEmpty()) {
         this.rbPreOrder((RBNode<T>) this.getRoot(), 0, array);
      }
      return array;
   }

   private int rbPreOrder(RBNode<T> node, int i, RBNode<T>[] array) {
      if (node != null) {
         if (!node.isEmpty()) {
            array[i++] = node;
            i = this.rbPreOrder((RBNode<T>) node.getLeft(), i, array);
            i = this.rbPreOrder((RBNode<T>) node.getRight(), i, array);
         }
      }
      return i;
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node == root) {
         node.setColour(Colour.BLACK);
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> tio = getTio(node);
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();

      if (tio.getColour() == Colour.RED) {
         ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
         tio.setColour(Colour.BLACK);
         avo.setColour(Colour.RED);
         fixUpCase1(avo);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> pai = (RBNode<T>) node.getParent();
      RBNode<T> next = node;

      if (!isLeftChild(node) && isLeftChild(pai)) {
         this.leftRotation(pai);
         next = (RBNode<T>) node.getLeft();

      } else if (isLeftChild(node) && !isLeftChild(pai)) {
         this.rightRotation(pai);
         next = (RBNode<T>) node.getRight();
      }
      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> avo = (RBNode<T>) node.getParent().getParent();

      ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
      avo.setColour(Colour.RED);

      if (isLeftChild(node))
         this.rightRotation(avo);
      else {
         this.leftRotation(avo);
      }
   }

   private void rightRotation(RBNode<T> node) {
      if (node != null && !node.isEmpty()) {

         RBNode<T> aux = (RBNode<T>) Util.rightRotation(node);
         if (node.equals(getRoot())) {
            this.root = aux;
         }
      }
   }

   private void leftRotation(RBNode<T> node) {
      if (node != null && !node.isEmpty()) {
         RBNode<T> aux = (RBNode<T>) Util.leftRotation(node);
         if (node.equals(getRoot())) {
            this.root = aux;
         }
      }
   }

   private RBNode<T> getTio(RBNode<T> node) {
      if (node.getParent().getParent().getLeft() == node.getParent()) {
         return (RBNode<T>) node.getParent().getParent().getRight();
      }
      return (RBNode<T>) node.getParent().getParent().getLeft();
   }

   private boolean isLeftChild(RBNode<T> node) {
      return node.getParent().getLeft() == node;
   }

}
