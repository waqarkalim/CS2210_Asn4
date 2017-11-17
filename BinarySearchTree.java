public class BinarySearchTree implements BinarySearchTreeADT {

	private BinaryNode root;
	private int size = 0;

	public BinarySearchTree() {
		this.root = null;
	}

//	public Pixel get(BinaryNode r, Location key) {
//		boolean found = false;
//		System.out.println("r  : (" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")");
//		while (r.getData() != null) {
//			System.out.println("r  : (" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")");
//			System.out.println("key: (" + key.xCoord() + ", " + key.yCoord() + ")");
//			if (r.getData().getLocation().compareTo(key) == 0) {
//				found = true;
//				return r.getData();
//			} else if (r.getData().getLocation().compareTo(key) == 1) {
//				r = r.getLeft();
//			} else {
//				r = r.getRight();
//			}
//		}
//		return found ? r.getData() : null;
////		return find(r, key).getData();
//	}
	
	public Pixel get(BinaryNode r, Location key) {
		if (find(r, key) == null) {
			return null;
		} else {
			return find(r, key).getData();
		}
	}
	
//	public Pixel get(BinaryNode r, Location key) {
//
//		if (size > 1) {
//			if (r.getData().getLocation().compareTo(key) == 0) {
//				if (r.isLeaf()) {
//					System.out.println("The value of the leaf is (" + key.xCoord() + ", " + key.yCoord() + ") , "
//							+ r.getData().getColor());
//					return r.getData();
//				}
//				System.out.println(key + ", " + r.getData().getColor());
//				return (new Pixel(key, r.getData().getColor()));
//			}
//
//			else if (r.getData().getLocation().compareTo(key) == -1) {
//				System.out.println("compareTo gives -1");
//				get(r.getRight(), key);
//			}
//
//			else if (r.getData().getLocation().compareTo(key) == 1) {
//				System.out.println("compareTo gives 1 and the key is (" + key.xCoord() + ", " + key.yCoord() + ")");
//				get(r.getLeft(), key);
//			}
//			System.out.println("Going to return null");
//			return null;
//		}
//
//		if (r.getData().getLocation().compareTo(key) == 0) {
//			return r.getData();
//		}
//		return null;
//	}

	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

		if (size == 0) { // BST is empty, so just make r the root of the tree,
							// what could possibly go wrong? :')
//			System.out.println("The tree is empty and one thing is being put into it");
			root = new BinaryNode(data, null, null, r);
			size++;
			return;
		}
		if (r.getData().getLocation().compareTo(data.getLocation()) == 0) {
			throw new DuplicatedKeyException(null);
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == -1) {

			if (r.getRight() == null) {
				r.setRight(new BinaryNode(data, null, null, r));
			}

			else {
				put(r.getRight(), data);
			}
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == 1) {

			if (r.getLeft() == null) {
				r.setLeft(new BinaryNode(data, null, null, r));
			}

			else {
				put(r.getLeft(), data);
			}
		}
	}

	// public void remove(BinaryNode r, Location key) throws InexistentKeyException
	// {
	//
	// BinaryNode p = find(r, key);
	//
	// if (p.isLeaf()) {
	// throw new InexistentKeyException(null);
	// } else {
	// if ((p.getLeft()).isLeaf()) {
	// if (p == root) {
	// BinaryNode cprime = p.getRight();
	// root = cprime;
	// cprime.setParent(null);
	// } else {
	// BinaryNode cprime = p.getRight();
	// p.getParent().setRight(cprime);
	// cprime.setParent(p.getParent());
	// }
	// } else if ((p.getRight().isLeaf())) {
	// if (p == root) {
	// BinaryNode cprime = p.getLeft();
	// root = cprime;
	// cprime.setParent(null);
	// } else {
	// BinaryNode cprime = p.getLeft();
	// p.getParent().setLeft(cprime);
	// cprime.setParent(p.getParent());
	// }
	// } else {
	// BinaryNode s = smallestnode(p.getRight());
	// p.setData(new Pixel(s.getData().getLocation(), s.getData().getColor()));
	// (s.getRight()).setParent(s.getParent());
	// }
	// }
	//
	// }

	// public void remove(BinaryNode r, Location key) throws InexistentKeyException
	// {
	// System.out.println("Remove starts");
	// if (find(r, key).isLeaf()) {
	// System.out.println("Throws an InexistentKeyException1");
	// throw new InexistentKeyException(null);
	// }
	// System.out.println("(" + r.getData().getLocation().xCoord() + ", " +
	// r.getData().getLocation().yCoord() + ")");
	// System.out.println("Remove starts 1");
	// if (r.getData().getLocation().compareTo(key) == 0) {
	// System.out.println("The compareTo value is 0");
	// if (r.getParent().getData().getLocation().compareTo(key) == -1) {
	// System.out.println("The parent node's compareTo value is -1");
	// r.getParent().setRight(null);
	// size--;
	// return;
	// }
	//
	// if (r.getParent().getData().getLocation().compareTo(key) == 1) {
	// System.out.println("The parent node's compareTo value is 1");
	// r.getParent().setLeft(null);
	// System.out.println("The right child of parent is removed");
	// size--;
	// return;
	// }
	// }
	//
	// else if (r.getData().getLocation().compareTo(key) == -1) {
	// System.out.println("The compareTo value is -1");
	// remove(r.getLeft(), key);
	// return;
	// }
	//
	// else if (r.getData().getLocation().compareTo(key) == 1) {
	// System.out.println("The compareTo value is 1");
	// remove(r.getRight(), key);
	// return;
	// }
	//
	// System.out.println("Throws an InexistentKeyException");
	// throw new InexistentKeyException(null);
	// }

	// GOOD REMOVE FUNCTION BELOW
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
//		System.out.println("(" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")"
//				+ " and the key value is (" + key.xCoord() + ", " + key.yCoord() + ")");

//		if (r == null){
		if (r.isLeaf() && (r.getData().getLocation().xCoord() != key.xCoord()) && (r.getData().getLocation().yCoord() != key.yCoord())) {
//			System.out.println("Throwing an InexistentKeyException");
			throw new InexistentKeyException(null);
		}
		
		if (r.isLeaf()) {
//			System.out.println("The node is a leaf node");
			if (r.getParent().getData().getLocation().compareTo(key) == -1) { // Change to -1 to improve, don't know why?
//				System.out.println("The compareTo value of the parent node is 1");
				r.getParent().setLeft(null);
				size--;
				return;
			} else if (r.getParent().getData().getLocation().compareTo(key) == 1) { // Change to 1 to improve, don't know why?
//				System.out.println("The compareTo value of the parent node is -1");
				r.getParent().setRight(null);
				size--;
				return;
			}
		} else if (r.getData().getLocation().compareTo(key) == -1) {
//			System.out.println("The compareTo value of this node is -1");
			remove(r.getRight(), key);
			return;
		} else if (r.getData().getLocation().compareTo(key) == 1) {
//			System.out.println("The compareTo value of this node is 1");
			remove(r.getLeft(), key);
			return;
		}
	}
	
//	public void remove(BinaryNode r, Location key) {
//		if (r.getData().getLocation().compareTo(key) == 1) {
//			remove(r.getLeft(), key);
//		} else if (r.getData().getLocation().compareTo(key) == -1) {
//			remove(r.getRight(), key);
//		} else {
//			if (r.isLeaf()) {
//				if (r == (r.getParent()).getLeft()) {
//					r.getParent().setLeft(null);
//				} else {
//					r.getParent().setRight(null);
//				}
//				size--;
//				return;
//			} else {
//				System.out.println("Get till here");
//				BinaryNode successor = successorNode(r, key);
//				successor.setLeft(r.getLeft());
//				successor.setRight(r.getRight());
//				successor.setParent(r.getParent());
//				(successor.getParent()).setRight(null);
//				size--;
//				return;
//			}
//		}
//	}
	
//	public Pixel successor(BinaryNode r, Location key) {
//		System.out.println("r : (" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")");
//		System.out.println("key: (" + key.xCoord() + ", " + key.yCoord() + ")");
//		BinaryNode p = find(r, key);
//		System.out.println("Find method returns something");
//		System.out.println(p.getData().getColor());
//		
//		if (!p.isLeaf() && (!(p.getRight()).isLeaf())) {
//			System.out.println("First if condition works");
//			return (smallest(p.getRight()));
//		} else {
//			BinaryNode pprime = p.getParent();
//			while ((pprime != null) && (pprime.getRight() == p)) {
//				p = pprime;
//				pprime = pprime.getParent();
//			}
//			if (pprime == null) {
//				return null;
//			} else {
//				return p.getData();
//			}
//		}
//	}
	
//	public Pixel successor(BinaryNode r, Location key) {
//		System.out.println("r  : (" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")");
//		System.out.println("key: (" + key.xCoord() + ", " + key.yCoord() + ")");
//		BinaryNode p = find(r, key);
//		System.out.println("p  : (" + p.getData().getLocation().xCoord() + ", " + p.getData().getLocation().yCoord() + ")");
//		if (r.getData() == null) {
//			System.out.println("The data in r is NULL");
//			return null;
//		}
//		if (p.isLeaf() && (p == p.getParent().getLeft())) {
//			return p.getParent().getData();
//		}
////		if (p.isLeaf() && (p == p.getParent().getRight())) {
////			System.out.println("p is a leaf node");
////			System.out.println("The compareTo value inside the isLeaf() condition is " + r.getData().getLocation().compareTo(new Location(2, 3)));
////			return successor(p.getParent(), key)
////		} 
//		if (r.getData().getLocation().compareTo(key) <= 0) {
//			System.out.println("compareTo value is -1 or 0");
//			if (r.getRight() != null){
//				System.out.println("The right child is not NULL");
//				return successor(r.getRight(), key);
//			} else {
//				System.out.println("The right child is NULL");
//				return null;
//			}
//		} else {
//			System.out.println("compareTo value is 1");
//			if (r.getLeft() != null) {
//				System.out.println("The left child is not NULL");
//				return successor(r.getLeft(), key);
//			} else {
//				System.out.println("The left child is NULL");
//				return null;
//			}
//		}
//	}	
	
	public Pixel successor(BinaryNode r, Location key) {
		if (r == null) {
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
		if (r == null) {
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
	
	
//	public Pixel successor(BinaryNode r, Location key) {
//		System.out.println("Work till here");
//		BinaryNode p = find(r, key);
//		if (p == null) {
//			return null;
//		}
//		System.out.println("Work till here1");
//		System.out.println(p);
//		System.out.println(p.getRight());
//		Pixel s = smallest(p.getRight());
//		System.out.println("Work till here2");
//		return s;
//		
//	}
	
	public Pixel predecessor(BinaryNode r, Location key) {
		if (r == null) {
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

	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		System.out.println("Enter the smallest() method");
		if (size == 0) {
			throw new EmptyTreeException();
		}
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r.getData();
			}
			System.out.println("Throwing an EmptyTreeException");
		} else {
			return smallest(r.getLeft());
		}

		return null;
	}

	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r.getData();
			}

			throw new EmptyTreeException();
		} else {
			largest(r.getRight());
		}

		return null;
	}

	
	public BinaryNode getRoot() {
		return this.root;
	}

//	private BinaryNode find(BinaryNode r, Location key) {
//
//		if (size > 1) {
//			if (r.getData().getLocation().compareTo(key) == 0) {
//				if (r.isLeaf()) {
//					System.out.println("The value of the leaf is (" + key.xCoord() + ", " + key.yCoord() + ") , "
//							+ r.getData().getColor());
//					return r;
//				}
//				System.out.println(key + ", " + r.getData().getColor());
//				return (new BinaryNode(new Pixel(key, r.getData().getColor()), r.getLeft(), r.getRight(),
//						r.getParent()));
//			}
//
//			else if (r.getData().getLocation().compareTo(key) == -1) {
//				System.out.println("compareTo gives -1");
//				find(r.getRight(), key);
//			}
//
//			else if (r.getData().getLocation().compareTo(key) == 1) {
//				System.out.println("compareTo gives 1 and the key is (" + key.xCoord() + ", " + key.yCoord() + ")");
//				find(r.getLeft(), key);
//			}
//			System.out.println("Going to return null");
//			return null;
//		}
//		System.out.println("Size is " + size);
//		if (size == 0){
//			return null;
//		} else {
//			return r;
//		}
////		if (r.getData().getLocation().compareTo(key) == 0) {
////			return r;
////		}
////		return null;
//	}
	
	private BinaryNode find(BinaryNode r, Location key) {
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