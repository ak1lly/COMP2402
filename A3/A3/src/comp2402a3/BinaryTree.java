package comp2402a3;

import java.util.*;

public class BinaryTree<Node extends BinaryTree.BTNode<Node>> {

	public static class BTNode<Node extends BTNode<Node>> {
		public Node left;
		public Node right;
		public Node parent;
	}

	/**
	 * An extension of BTNode that you can actually instantiate.
	 */
	protected static class EndNode extends BTNode<EndNode> {
			public EndNode() {
				this.parent = this.left = this.right = null;
			}
	}

	/**
	 * Used to make a mini-factory
	 */
	protected Node sampleNode;

	/**
	 * The root of this tree
	 */
	protected Node r;

	/**
	 * This tree's "null" node
	 */
	protected Node nil;

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 * @param nil - a node that will be used in place of null
	 */
	public BinaryTree(Node sampleNode, Node nil) {
		this.sampleNode = sampleNode;
		this.nil = nil;
		r = nil;
	}

	/**
	 * Create a new instance of this class
	 * @param sampleNode - a sample of a node that can be used
	 * to create a new node in newNode()
	 */
	public BinaryTree(Node sampleNode) {
		this.sampleNode = sampleNode;
	}

	/**
	 * Allocate a new node for use in this tree
	 * @return newly created node
	 */
	@SuppressWarnings({"unchecked"})
	protected Node newNode() {
		try {
			Node u = (Node)sampleNode.getClass().getDeclaredConstructor().newInstance();
			u.parent = u.left = u.right = nil;
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Construct a random binary tree
	 * @return an n-node BinaryTree that has the shape of a random
	 * binary search tree.
	 */
	public static BinaryTree<EndNode> randomBST(int n) {
		Random rand = new Random();
		EndNode sample = new EndNode();
		BinaryTree<EndNode> t = new BinaryTree<EndNode>(sample);
		t.r = randomBSTHelper(n, rand);
		return t;
	}

	protected static EndNode randomBSTHelper(int n, Random rand) {
		if (n == 0) {
			return null;
		}
		EndNode r = new EndNode();
		int ml = rand.nextInt(n);
		int mr = n - ml - 1;
		if (ml > 0) {
			r.left = randomBSTHelper(ml, rand);
			r.left.parent = r;
		}
		if (mr > 0) {
			r.right = randomBSTHelper(mr, rand);
			r.right.parent = r;
		}
		return r;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringHelper(sb, r);
		return sb.toString();
	}

	protected void toStringHelper(StringBuilder sb, Node u) {
		if (u == null) {
			return;
		}
		sb.append('(');
		toStringHelper(sb, u.left);
		toStringHelper(sb, u.right);
		sb.append(')');
	}

	/**
	 * Tree empty or not
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return r == nil;
	}

	/**
	 * Make this tree into the empty tree
	 */
	public void clear() {
		r = nil;
	}

	/**
	 * Compute the depth (distance to the root) of u
	 * @param u
	 * @return the distanct between u and the root, r
	 */
	public int depth(Node u) {
		int d = 0;
		while (u != r) {
			u = u.parent;
			d++;
		}
		return d;
	}

	/**
	 * Demonstration of a recursive traversal
	 * @param u
	 */
	public void traverse(Node u) {
		if (u == nil) return;
		traverse(u.left);
		traverse(u.right);
	}

	/**
	 * Demonstration of a non-recursive traversal
	 */
	public void traverse2() {
		Node u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					next = u.right;
				}	else {
					next = u.parent;
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					next = u.parent;
				}
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
	}

	/**
	 * Demonstration of a breadth-first traversal
	 */
	public void bfTraverse() {
		Queue<Node> q = new LinkedList<Node>();
		if (r != nil) q.add(r);
		while (!q.isEmpty()) {
			Node u = q.remove();
			if (u.left != nil) q.add(u.left);
			if (u.right != nil) q.add(u.right);
		}
	}

	/**
	 * Compute the size (number of nodes) of this tree
	 * @warning uses recursion so could cause a stack overflow
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size(r);
	}

	/**
	 * @return the size of the subtree rooted at u
	 */
	protected int size(Node u) {
		if (u == nil) return 0;
		return 1 + size(u.left) + size(u.right);
	}

	/**
	 * Compute the maximum depth of any node in this tree
	 * @return the maximum depth of any node in this tree
	 */
	public int height() {
		return height(r);
	}

	/**
	 * @return the height of the subtree rooted at u
	 */
	protected int height(Node u) {
		if (u == nil) return -1;
		return 1 + Math.max(height(u.left), height(u.right));
	}

	public int leafAndOnlyLeaf() {


		if (r == null) {
			return 0;
		}

		Stack<Node> nodes = new Stack<>();
		nodes.push(r);
		int count = 0;

		while (!nodes.isEmpty()) {
			Node t = nodes.pop();

			if (t.left == null && t.right == null) {
				count++; //leaf
			}
			if (t.right != null) {
				nodes.push(t.right);
			}
			if (t.left != null) {
				nodes.push(t.left);
			}

		}

		return count;
	}

	public int dawnOfSpring() {
		// TODO: Your code goes here, must avoid recursion

		if (r == nil) {
			return -1;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(r);

		int d = 0;

		while (!q.isEmpty()) {
			int nodeCount = q.size();

			for (int i = 0; i < nodeCount; i++) {
				Node u = q.size() > 0 ? q.remove() : null;

				if (u.left == nil && u.right == nil) {
					return d;
				}
				if (u.left != nil) {
					q.add(u.left);
				}
				if (u.right != nil) {
					q.add(u.right);
				}
			}

			d++;
		}

		return -1;
	}

	public int monkeyLand() {
		// TODO: Your code goes here, must avoid recursion

		if (r == nil) {
			return 0;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(r);
		int maxCount = 1;

		while (!q.isEmpty()) {
			int nodeCount = q.size();

			if (nodeCount > maxCount) {
				maxCount = nodeCount;
			}

			for (int i = 0; i < nodeCount; i++) {
				Node u = q.size() > 0 ? q.remove() : null;

				if (u.left != nil) {
					q.add(u.left);
				}

				if (u.right != nil) {
					q.add(u.right);
				}
			}
		}

		return maxCount;
	}

	public String bracketSequence() {
		StringBuilder sb = new StringBuilder();
		// TODO: Your code goes here, use sb.append(), must avoid recursion

		if (r == null) {
			return ".";
		}
		Node u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				sb.append("(");
				if (u.left != nil) {
					next = u.left;
				} else if (u.right != nil) {
					sb.append(".");
					next = u.right;
				}	else {
					sb.append("..");
					next = u.parent;
					sb.append(")");
				}
			} else if (prev == u.left) {
				if (u.right != nil) {
					next = u.right;
				} else {
					sb.append(".");
					next = u.parent;
					sb.append(")");
				}
			} else {
				next = u.parent;
				sb.append(")");
			}
			prev = u;
			u = next;
		}
		return sb.toString();
	}
}
