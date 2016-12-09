import java.util.Scanner;
import java.util.Stack;
import java.io.*;

class TNode<T> {
	private T key;
	private TNode<T> left, right;

	public TNode(T key) {
		this(key, null, null);
	}

	public TNode(T key, TNode<T> left, TNode<T> right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public TNode<T> getLeft() {
		return left;
	}

	public void setLeft(TNode<T> left) {
		this.left = left;
	}

	public TNode<T> getRight() {
		return right;
	}

	public void setRight(TNode<T> right) {
		this.right = right;
	}
}

public class ExpressionTree {

	public static TNode<Character> buildExpTree(String postfixExp) {
		char c;
		TNode<Character> newNode, newLeft, newRight;
		Stack<TNode<Character>> s = new Stack<TNode<Character>>();
		int i = 0, len = postfixExp.length();

		while (i != len) {
			while (postfixExp.charAt(i) == ' ' || postfixExp.charAt(i) == '\t')
				i++;

			if (i == len)
				break;
			c = postfixExp.charAt(i);
			i++;

			if (c == '+' || c == '-' || c == '*' || c == '/') {
				newRight = s.pop();
				newLeft = s.pop();

				newNode = new TNode<Character>(c, newLeft, newRight);
				s.push(newNode);
			} else {
				newNode = new TNode<Character>(c);
				s.push(newNode);
			}
		}

		if (!s.isEmpty())
			return s.pop();
		else
			return null;
	}

	public static <T> void inorderOutput(TNode<T> t) {
		if (t != null) {
			inorderOutput(t.getLeft());
			System.out.print(t.getKey() + " ");
			inorderOutput(t.getRight());
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(new FileReader("hw8inp.dat"));
		while (s.hasNextLine()) {
			String str = s.nextLine();
			TNode<Character> root = ExpressionTree.buildExpTree(str);
			ExpressionTree.inorderOutput(root);
			System.out.println();
		}
		s.close();
	}

}