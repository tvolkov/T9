class Trie<T> {
    TrieNode<T> root = new TrieNode<>()
    int numberOfKeys = 0

    def put(String key, T value){
        return put(key, value, root, 0)
    }

    def put(String key, T value, TrieNode node, int index){
        if (index == key.length()){
//            if (node.isReal)
//                return false
            node.isReal = true
            node.data.push(value)
            numberOfKeys++
            return true
        }
        char nodeKey = key.charAt(index)
        TrieNode child = node.children[nodeKey as int]
        if (!child){
            child = new TrieNode();
            node.children[nodeKey as int] = child
        }
        return put(key, value, child, index + 1)

    }

    def get(String key){
        return get(root, key, 0)
    }

    def get(TrieNode node, String key, int index){
        if (index == key.length())
            return node
        if (!node) return null
        char nodeKey = key.charAt(index)
        return get(node?.children[nodeKey as int], key, index + 1)
    }

    def contains(String key){
        return get(key) != null
    }

    def keysWithPrefix(String prefix){
        def results = []
        TrieNode node = get(prefix)
        collect(node, new StringBuilder(prefix), results)
        return results
    }

    def collect(TrieNode node, StringBuilder prefix, def results){
        if (!node) return
        if (node.isReal) results << prefix.toString()
        for (char nodeKey in 0..255){
            if (node.children[nodeKey as int] != null) {
                prefix.append(nodeKey)
                collect(node.children[nodeKey as int], prefix, results)
                prefix.deleteCharAt(prefix.length() - 1)
            }
        }
    }

    def valuesWithPrefix(String keyPrefix){
        def results = []
        TrieNode node = get(keyPrefix)
        collectValues(node, new StringBuilder(keyPrefix), results)
        return results
    }

    def collectValues(TrieNode node, StringBuilder prefix, def results){
        if (!node) return
        if (node.isReal) results << node.data
        for (char nodeKey in 0..255){
            if (node.children[nodeKey as int] != null){
                prefix.append(nodeKey)
                collectValues(node.children[nodeKey as int], prefix, results)
                prefix.deleteCharAt(prefix.length() - 1)
            }
        }
    }

    int size(){
        return numberOfKeys
    }
}

class TrieNode<T> {
    boolean isReal
    TrieNode[] children = new TrieNode[256]
    def data = []
}