class Trie {
    TrieNode root = new TrieNode()

    def put(String key){
        return put(key, root, 0)
    }

    def put(String key, TrieNode node, int index){
        char nodeKey = key.charAt(index)
        TrieNode child = node.children[nodeKey as int]
        if (!child){
            child = new TrieNode();
            node.children[nodeKey as int] = child
        }
        if (index == key.length() - 1){
            if (node.isReal)
                return false
            node.isReal = true
            return true
        } else {
            return put(key, child, index + 1)
        }

    }

    def get(String key){
        return get(root, key, 0)
    }

    def get(TrieNode node, String key, int index){
        char nodeKey = key.charAt(index)
        if (key.length() == index)
            return node
        return get(node.children[nodeKey], key, index + 1)
    }
}

class TrieNode {
    boolean isReal
    TrieNode[] children = new TrieNode[256]
}