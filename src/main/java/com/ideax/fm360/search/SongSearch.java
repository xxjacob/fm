package com.ideax.fm360.search;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.apache.lucene.search.spell.TermFreqPayloadIterator;
import org.apache.lucene.search.suggest.Lookup.LookupResult;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ideax.fm360.pojo.Song;

public class SongSearch {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    List<Song> songs = null;

    AnalyzingSuggester suggester;

    final String[] dummy = new String[0];

    public void build(List<Song> list) {
        this.songs = list;
        // Directory scdir;
        try {
            // scdir = new RAMDirectory();
            FmAnalyzer analyzer = new FmAnalyzer(Version.LUCENE_43);
            suggester = new AnalyzingSuggester(analyzer);
            suggester.build(new SongNameIterator());
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public String[] suggest(String key) {
        List<LookupResult> list = suggester.lookup(key, false, 10);
        if (list != null && list.size() > 0) {
            String[] docs = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                LookupResult res = list.get(i);
                docs[i] = res.key.toString() + "###" + ((res.payload == null) ? "" : res.payload.utf8ToString());
            }
            return docs;
        }
        return dummy;
    }

    final class SongNameIterator implements TermFreqPayloadIterator {
        private final BytesRef spare = new BytesRef();
        private final BytesRef payload = new BytesRef();
        int index = 0;
        Song c = null;

        public BytesRef next() throws IOException {
            if (index >= songs.size())
                return null;
            c = songs.get(index);
            spare.copyChars(c.getName());
            index++;
            return spare;
        }

        public Comparator<BytesRef> getComparator() {
            return null;
        }

        @Override
        public long weight() {
            return 0;
        }

        @Override
        public BytesRef payload() {
            if (c != null) {
                payload.copyChars(c.getId() + "###" + c.getArtist());
                return payload;
            }
            return null;
        }
    }

}
