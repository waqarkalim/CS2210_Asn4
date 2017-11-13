public class BinarySearchTree implements BinarySearchTreeADT {

	private BinaryNode root;
	private int size = 0;

	public BinarySearchTree() {
		this.root = null;
	}

	public Pixel get(BinaryNode r, Location key) {

		if (size > 1) {
			if (r.getData().getLocation().compareTo(key) == 0) {
				if (r.isLeaf()) {
					System.out.println("The value of the leaf is (" + key.xCoord() + ", " + key.yCoord() + ") , "
							+ r.getData().getColor());
					return r.getData();
				}
				System.out.println(key + ", " + r.getData().getColor());
				return (new Pixel(key, r.getData().getColor()));
			}

			else if (r.getData().getLocation().compareTo(key) == -1) {
				System.out.println("compareTo gives -1");
				get(r.getLeft(), key);
			}

			else if (r.getData().getLocation().compareTo(key) == 1) {
				System.out.println("compareTo gives 1 and the key is (" + key.xCoord() + ", " + key.yCoord() + ")");
				get(r.getRight(), key);
			}
			System.out.println("Going to return null");
			return null;
		}

		if (r.getData().getLocation().compareTo(key) == 0) {
			return r.getData();
		}
		return null;
	}

	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

		if (size == 0) { // BST is empty, so just make r the root of the tree,
							// what could possibly go wrong? :')
			System.out.println("The tree is empty and one thing is being put into it");
			root = new BinaryNode(data, null, null, r);
			size++;
			return;
		}
		if (r.getData().getLocation().compareTo(data.getLocation()) == 0) {
			throw new DuplicatedKeyException(null);
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == -1) {

			if (r.getLeft() == null) {
				r.setLeft(new BinaryNode(data, null, null, r));
			}

			else {
				put(r.getLeft(), data);
			}
		} else if (r.getData().getLocation().compareTo(data.getLocation()) == 1) {

			if (r.getRight() == null) {
				r.setRight(new BinaryNode(data, null, null, r));
			}

			else {
				put(r.getRight(), data);
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

	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
//		System.out.println("(" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")"
//				+ " and the key value is (" + key.xCoord() + ", " + key.yCoord() + ")");

		if (r.isLeaf() && (r.getData().getLocation().xCoord() != key.xCoord()) && (r.getData().getLocation().yCoord() != key.yCoord())) {
//			System.out.println("Throwing an InexistentKeyException");
			throw new InexistentKeyException(null);
		}
		
		if (r.isLeaf()) {
//			System.out.println("The node is a leaf node");
			if (r.getParent().getData().getLocation().compareTo(key) == -1) {
//				System.out.println("The compareTo value of the parent node is -1");
				r.getParent().setRight(null);
				size--;
				return;
			} else if (r.getParent().getData().getLocation().compareTo(key) == 1) {
//				System.out.println("The compareTo value of the parent node is 1");
				r.getParent().setLeft(null);
				size--;
				return;
			}
		} else if (r.getData().getLocation().compareTo(key) == 1) {
//			System.out.println("The compareTo value of this node is 1");
			remove(r.getRight(), key);
			return;
		} else if (r.getData().getLocation().compareTo(key) == -1) {
//			System.out.println("The compareTo value of this node is -1");
			remove(r.getLeft(), key);
			return;
		}
	}
	
	public Pixel successor(BinaryNode r, Location key) {
		System.out.println("r : (" + r.getData().getLocation().xCoord() + ", " + r.getData().getLocation().yCoord() + ")");
		System.out.println("key: (" + key.xCoord() + ", " + key.yCoord() + ")");
		BinaryNode p = find(r, key);
		System.out.println("Find method returns something");
		System.out.println(p.getData().getColor());
		if (p == null) {
			return null;
		}
		if (!p.isLeaf() && (!(p.getRight()).isLeaf())) {
			System.out.println("First if condition works");
			return (smallest(p.getRight()));
		} else {
			BinaryNode pprime = p.getParent();
			while ((pprime != null) && (pprime.getRight() == p)) {
				p = pprime;
				pprime = pprime.getParent();
			}
			if (pprime == null) {
				return null;
			} else {
				return p.getData();
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
	public BinaryNode successornode(BinaryNode r, Location key) {
		BinaryNode p = find(r, key);
		if (!p.isLeaf() && (!(p.getRight()).isLeaf())) {
			return (smallestnode(p.getRight()));
		} else {
			BinaryNode pprime = p.getParent();
			while ((pprime != null) && (pprime.getRight() == p)) {
				p = pprime;
				pprime = pprime.getParent();
			}
			if (pprime == null) {
				return null;
			} else {
				return p;
			}
		}
	}

	public Pixel predecessor(BinaryNode r, Location key) {
		BinaryNode p = find(r, key);
		if (!p.isLeaf() && (!(p.getLeft()).isLeaf())) {
			return smallest(p.getLeft());
		} else {
			BinaryNode pprime = p.getParent();
			while ((pprime != null) && (pprime.getLeft() == p)) {
				p = pprime;
				pprime = pprime.getParent();
			}
			if (pprime == null) {
				return null;
			} else {
				return p.getData();
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
//			throw new EmptyTreeException();
		} else {
			return smallest(r.getLeft());
		}

		return null;
	}

	private BinaryNode smallestnode(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r;
			}

			throw new EmptyTreeException();
		} else {
			smallestnode(r.getLeft());
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

	private Pixel largestnode(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r;
			}

			throw new EmptyTreeException();
		} else {
			largestnode(r.getRight());
		}

		return null;
	}

	public BinaryNode getRoot() {
		return this.root;
	}

	private BinaryNode find(BinaryNode r, Location key) {

		if (size > 1) {
			if (r.getData().getLocation().compareTo(key) == 0) {
				if (r.isLeaf()) {
					System.out.println("The value of the leaf is (" + key.xCoord() + ", " + key.yCoord() + ") , "
							+ r.getData().getColor());
					return r;
				}
				System.out.println(key + ", " + r.getData().getColor());
				return (new BinaryNode(new Pixel(key, r.getData().getColor()), r.getLeft(), r.getRight(),
						r.getParent()));
			}

			else if (r.getData().getLocation().compareTo(key) == -1) {
				System.out.println("compareTo gives -1");
				find(r.getLeft(), key);
			}

			else if (r.getData().getLocation().compareTo(key) == 1) {
				System.out.println("compareTo gives 1 and the key is (" + key.xCoord() + ", " + key.yCoord() + ")");
				find(r.getRight(), key);
			}
			System.out.println("Going to return null");
			return null;
		}
		System.out.println("Size is " + size);
		if (size == 0){
			return null;
		} else {
			return r;
		}
//		if (r.getData().getLocation().compareTo(key) == 0) {
//			return r;
//		}
//		return null;
	}
}