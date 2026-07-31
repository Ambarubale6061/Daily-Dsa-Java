import java.util.*;

public class SuffixAutomaton {
    static class State {
        int len, link;
        Map<Character, Integer> next = new HashMap<>();
    }

    State[] st;
    int size, last;

    public SuffixAutomaton(int maxlen) {
        st = new State[2 * maxlen];
        for (int i = 0; i < st.length; i++)
            st[i] = new State();
        st[0].len = 0;
        st[0].link = -1;
        size = 1;
        last = 0;
    }

    public void extend(char c) {
        int cur = size++;
        st[cur].len = st[last].len + 1;
        int p = last;
        while (p != -1 && !st[p].next.containsKey(c)) {
            st[p].next.put(c, cur);
            p = st[p].link;
        }
        if (p == -1)
            st[cur].link = 0;
        else {
            int q = st[p].next.get(c);
            if (st[p].len + 1 == st[q].len)
                st[cur].link = q;
            else {
                int clone = size++;
                st[clone].len = st[p].len + 1;
                st[clone].next = new HashMap<>(st[q].next);
                st[clone].link = st[q].link;
                while (p != -1 && st[p].next.get(c) == q) {
                    st[p].next.put(c, clone);
                    p = st[p].link;
                }
                st[q].link = st[cur].link = clone;
            }
        }
        last = cur;
    }
}