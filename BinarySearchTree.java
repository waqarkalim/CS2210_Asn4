public class BinarySearchTree implements BinarySearchTreeADT {

	private BinaryNode root;
	private int size = 0;

	public BinarySearchTree() {
		this.root = null;
	}
	
	public Pixel get(BinaryNode r, Location key) {
		if (find(r, key) == null) {
			return null;
		} else {
			return find(r, key).getData();
		}
	}

	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

		if (size == 0) { // BST is empty, so just make r the root of the tree,
							// what could possibly go wrong? :')
			root = new BinaryNode(data, null, null, r);
			size++;
			return;
		}
		if (r.getData().getLocation().compareTo(data.getLocation()) == 0) {
			throw new DuplicatedKeyException(null);
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == -1) {

			if (r.getRight() == null) {
				r.setRight(new BinaryNode(data, null, null, r));
				size++;
				return;
			}

			else {
				put(r.getRight(), data);
			}
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == 1) {

			if (r.getLeft() == null) {
				r.setLeft(new BinaryNode(data, null, null, r));
				size++;
				return;
			}

			else {
				put(r.getLeft(), data);
			}
		}
	}

	// GOOD REMOVE FUNCTION BELOW
//	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
////		System.out.println("(" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")"
////				+ " and the key value is (" + key.xCoord() + ", " + key.yCoord() + ")");
//
////		if (r == null){
//		if (r.isLeaf() && (r.getData().getLocation().xCoord() != key.xCoord()) && (r.getData().getLocation().yCoord() != key.yCoord())) {
////			System.out.println("Throwing an InexistentKeyException");
//			throw new InexistentKeyException(null);
//		}
//		
//		if (r.isLeaf()) {
////			System.out.println("The node is a leaf node");
//			if (r.getParent().getData().getLocation().compareTo(key) == -1) { // Change to -1 to improve, don't know why?
////				System.out.println("The compareTo value of the parent node is 1");
//				r.getParent().setLeft(null);
//				size--;
//				return;
//			} else if (r.getParent().getData().getLocation().compareTo(key) == 1) { // Change to 1 to improve, don't know why?
////				System.out.println("The compareTo value of the parent node is -1");
//				r.getParent().setRight(null);
//				size--;
//				return;
//			}
//		} else if (r.getData().getLocation().compareTo(key) == -1) {
////			System.out.println("The compareTo value of this node is -1");
//			remove(r.getRight(), key);
//			return;
//		} else if (r.getData().getLocation().compareTo(key) == 1) {
////			System.out.println("The compareTo value of this node is 1");
//			remove(r.getLeft(), key);
//			return;
//		}
//	}
	
	
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		BinaryNode p = find(r, key);
		
		if (root.equals(p)) {
			r = null;
			size--;
			return;
		}
		
		if (r.isLeaf() && (r.getData().getLocation().xCoord() != key.xCoord()) && (r.getData().getLocation().yCoord() != key.yCoord())) {
			throw new InexistentKeyException(null);
		} else {
			if ((p.getLeft() == null) && ((p.getRight() != null))) {
				if (p.equals(r)) {
					BinaryNode otherChild = p.getRight();
					root = otherChild;
					otherChild.setParent(null);
				} else {
					BinaryNode otherChild = p.getRight();
					p.setParent(otherChild);
					otherChild.setParent(p.getParent());
				}
				size--;
				return;
			} else if ((p.getRight() == null) && (p.getLeft() != null)) {
				if (p.equals(r)) {
					BinaryNode otherChild = p.getLeft();
					root = otherChild;
					otherChild.setParent(null);
				} else {
					BinaryNode otherChild = p.getLeft();
					p.setParent(otherChild);
					otherChild.setParent(p.getParent());
				}
				size--;
				return;
			} else {
				if ((p != null) && (p.getLeft() == null) && (p.getRight() == null)) {
					if (r.getData().getLocation().compareTo(key) == 1) {
						r.setLeft(null);
						size--;
						return;
					} else if (r.getData().getLocation().compareTo(key) == -1) {
						r.setRight(null);
						size--;
						return;
					}
				}
				BinaryNode smallest = smallestNode(p.getRight());
				p.setData(new Pixel(smallest.getData().getLocation(), smallest.getData().getColor()));
				smallest.setParent((smallest.getRight()).getParent());
			}
		}
	}
	
	
	// BEST REMOVE FUNCTION YET BELOW
//	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
//		System.out.println(r + ", " + r.getParent());
//		if (r.isLeaf() && (r.getData().getLocation().xCoord() != key.xCoord()) && (r.getData().getLocation().yCoord() != key.yCoord())) {
//			System.out.println("Throwing an inexistent key exception");
//			throw new InexistentKeyException(null);
//		}
//		if (size == 1) {
//			System.out.println("Removing root as that is the only node in tree");
//			r = null;
//			size--;
//			return;
//		}
//		
//		if (r.getData().getLocation().compareTo(key) == 1) {
//			System.out.println("The compareTo value is 1, move left");
//			remove(r.getLeft(), key);
//		} else if (r.getData().getLocation().compareTo(key) == -1) {
//			System.out.println("The compareTo value is -1, move right");
//			remove(r.getRight(), key);
//		} else {
//			if (!(r.isLeaf())) {
//				System.out.println("r is not a leaf");
////				System.out.println(r.getParent());
//				System.out.println("(" + r.getParent().getData().getLocation().xCoord() + ", " + r.getParent().getData().getLocation().yCoord() + ")");
//				if (r.getParent().getData().getLocation().compareTo(key) == 1) {
//					System.out.println("r.parent compareTo value is 1");
//					BinaryNode predecessor = predecessorNode(r.getParent(), key);
//					(predecessor.getParent()).setRight(null);
//					predecessor.setLeft(r.getLeft());
//					predecessor.setRight(r.getRight());
//					(r.getParent()).setLeft(predecessor);
//					size--;
//					return;
//					
//				} else if (r.getParent().getData().getLocation().compareTo(key) == -1) {
//					System.out.println("r.parent compareTo value is -1");
//					BinaryNode successor = successorNode(r.getParent(), key);
//					(successor.getParent()).setLeft(null);
//					successor.setRight(r.getRight());
//					successor.setLeft(r.getLeft());
//					(r.getParent()).setRight(successor);
//					size--;
//					return;
//					
//				}
//			} else {
//				System.out.println("r is a leaf");
//				if (r.getParent().getData().getLocation().compareTo(key) == 1) {
//					(r.getParent()).setLeft(null);
//					size--;
//					return;
//				} else if (r.getParent().getData().getLocation().compareTo(key) == -1) {
//					(r.getParent()).setRight(null);
//					size--;
//					return;
//				}
//			}
//		}
//	}
	

	public Pixel successor(BinaryNode r, Location key) {
//		System.out.println(r);
		if ((r == null)) {
			return null;
		} 
		if (r.getData().getLocation().compareTo(key) <= 0) {
			return successor(r.getRight(), key);
		} else {
			Pixel next = successor(r.getLeft(), key); 
			if (next == null) {
				return r.getData();
			} else {
				return next;
			}
		}
		
	}
	
	public BinaryNode successorNode(BinaryNode r, Location key) {
		if ((r == null)) {
			return null;
		} 
		if (r.getData().getLocation().compareTo(key) <= 0) {
			return successorNode(r.getRight(), key);
		} else {
			BinaryNode next = successorNode(r.getLeft(), key); 
			if (next == null) {
				return r;
			} else {
				return next;
			}
		}
		
	}
	
	public Pixel predecessor(BinaryNode r, Location key) {
		if ((r == null)) {
			return null;
		} 
		if (r.getData().getLocation().compareTo(key) >= 0) {
			return predecessor(r.getLeft(), key);
		} else {
			
			Pixel next = predecessor(r.getRight(), key); 
			if (next == null) {
				return r.getData();
			} else {
				return next;
			}
		}
		
	}
	
	public BinaryNode predecessorNode(BinaryNode r, Location key) {
		if ((r == null)) {
			return null;
		} 
		if (r.getData().getLocation().compareTo(key) >= 0) {
			return predecessorNode(r.getLeft(), key);
		} else {
			
			BinaryNode next = predecessorNode(r.getRight(), key); 
			if (next == null) {
				return r;
			} else {
				return next;
			}
		}
		
	}

	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		
		if (r != null) {
			while (r.getLeft() != null) {
				r = r.getLeft();
			}
			return r.getData();
		} else {
			return null;
		}
		
	}
	
public BinaryNode smallestNode(BinaryNode r) throws EmptyTreeException {
		
		if (r != null) {
			while (r.getLeft() != null) {
				r = r.getLeft();
			}
			return r;
		} else {
			return null;
		}
		
	}

	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		
		if (r != null) {
			while (r.getRight() != null) {
				r = r.getRight();
			}
			return r.getData();
		} else {
			return null;
		}
		
	}

	
	public BinaryNode getRoot() {
		return this.root;
	}
	
	public BinaryNode find(BinaryNode r, Location key) {
		boolean found = false;
		while (r != null) {
			if (r.getData().getLocation().compareTo(key) == 0) {
				found = true;
				return r;
			} else if (r.getData().getLocation().compareTo(key) == 1) {
				r = r.getLeft();
			} else {
				r = r.getRight();
			}
		}
		if (!found) {
			return null;
		}
		return r;
	}
}
	
	