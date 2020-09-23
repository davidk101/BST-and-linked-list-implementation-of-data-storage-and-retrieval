
class BST{

    Node root;
    private class Node{

        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String k){

        	keyword = k;
        }

        private void update(Record r){

        	if(record == null) {
        		record = r;
        	}
        	else{

        		r.next = record; record = r;
        	}
        }
    }

    public BST(){

        this.root = null;
    }

    public void insert(String keyword, FileData fd){

        Record record = new Record(fd.id, fd.title, fd.author, null);

		insert(keyword, record, root);
    }

    private void insert(String keyword, Record record, Node root){

    	if(root == null) {

    		Node node = new Node(keyword);
    		node.update(record);
    		this.root = node;
    	}
    	else if(keyword.compareTo(root.keyword) < 0 && root.l == null) {

    		Node node = new Node(keyword);
    		node.update(record);
    		root.l = node;
    	}
    	else if(keyword.compareTo(root.keyword) < 0 && root.l != null) {

    		insert(keyword, record, root.l);
    	}
    	else if(keyword.compareTo(root.keyword) > 0 && root.r == null) {

    		Node newNode = new Node(keyword);
    		newNode.update(record);
    		root.r = newNode;
    	}
    	else if(keyword.compareTo(root.keyword) > 0 && root.r != null) {

    		insert(keyword, record, root.r);
    	}

    	else {

    		root.update(record);
    	}
    }

    public boolean contains(String keyword){

    	return contains(keyword, root);
    }

    public boolean contains(String keyword, Node root){

    	if(root == null) {

    		return false;

    	}
    	else if(keyword.compareTo(root.keyword) < 0) {

    		return contains(keyword, root.l);

    	}

    	else if(keyword.compareTo(root.keyword) > 0) {

    		return contains(keyword, root.r);
    	}

    	else {

    		return true;

    	}
    }

    public Record get_records(String keyword){

    	Node current = root;

    	while(current != null){

    		if(keyword.compareTo(current.keyword) < 0) {

    			current  = current.l;

    		}

    		else if(keyword.compareTo(current.keyword) > 0) {

    			current = current.r;
    		}

    		else{

    			return current.record;
    		}
    	}

    	return null;
    }

    public void delete(String keyword){

    	delete(keyword, root);
    }

    public Node delete(String keyword, Node current){

    	if(current == null) {

    		return null;
    	}

    	else if(keyword.compareTo(current.keyword) < 0) {

    		current.l = delete(keyword, current.l);
    	}

    	else if(keyword.compareTo(current.keyword) > 0) {

    		current.r = delete(keyword, current.r);

    	}

    	else {

    		if(current.l == null) {

    			return current.r;
    		}
    		else if(current.r == null) {

    			return current.l;
    		}

    		current.keyword = minimumNode(current.r);

    		current.r = delete(current.keyword, current.r);
    	}

    	return current;
    }

    public String minimumNode(Node current){

    	String min = current.keyword;

		while(current.l != null){

			min = current.l.keyword;
			current = current.l;
		}

		return min;
    }

    public void print(){

        print(root);
    }

    private void print(Node t){

        if (t!=null){

            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        }
    }


}
