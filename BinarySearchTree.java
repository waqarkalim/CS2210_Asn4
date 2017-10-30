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

	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		if (r.getData().getLocation().compareTo(key) == 0) {
			System.out.println("The compareTo value is 0");
			if (r.getParent().getData().getLocation().compareTo(key) == -1) {
				System.out.println("The parent node's compareTo value is -1");
				r.getParent().setRight(null);
				size--;
				return;
			}

			if (r.getParent().getData().getLocation().compareTo(key) == 1) {
				System.out.println("The parent node's compareTo value is 1");
				r.getParent().setLeft(null);
				System.out.println("The right child of parent is removed");
				size--;
				return;
			}
		}

		else if (r.getData().getLocation().compareTo(key) == -1) {
			System.out.println("The compareTo value is -1");
			remove(r.getLeft(), key);
		}

		else if (r.getData().getLocation().compareTo(key) == 1) {
			System.out.println("The compareTo value is 1");
			remove(r.getRight(), key);
		}

		throw new InexistentKeyException(null);
	}

	public Pixel sucessor(BinaryNode r, Location key) {
		if (r.isLeaf()) {
			return r.getData();
		} else {

		}
	}

	public Pixel predecessor(BinaryNode r, Location key) {
		;
	}

	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r.getData();
			}

			throw new EmptyTreeException(null);
		} else {
			smallest(r.getLeft());
		}

		return null;
	}

	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()) {

			if (r.getData() != null) {
				return r.getData();
			}

			throw new EmptyTreeException(null);
		} else {
			largest(r.getRight());
		}

		return null;
	}

	public BinaryNode getRoot() {
		return this.root;
	}
}
