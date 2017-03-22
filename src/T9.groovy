def map = ['2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl', '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz']

def words = ['bat', 'cat', 'mat', 'hat', 'hello', 'world']

def encodedWord = '228'
//def results = []
//words.each {word ->
//    if (word.length() == encodedWord.length()){
//        boolean match = true
//        for (i in 0..word.length() - 1){
//            if (!map[encodedWord[i]].contains(word[i])){
//                match = false
//                break
//            }
//        }
//        if (match){
//            results << word
//        }
//    }
//}
//
//println results

Trie trie = new Trie()
println trie.put('bat')


TrieNode node =  trie.get('bat')
println node.isReal
println node.children

node = trie.get('cat')
println node?.isReal
println node?.children

assert trie.contains('bat')
assert !trie.contains('cat')