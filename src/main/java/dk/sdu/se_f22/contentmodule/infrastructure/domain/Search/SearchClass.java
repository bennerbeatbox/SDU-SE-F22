package dk.sdu.se_f22.contentmodule.infrastructure.domain.Search;


import dk.sdu.se_f22.contentmodule.infrastructure.domain.Indexing.HTMLSite;
import dk.sdu.se_f22.searchmodule.infrastructure.interfaces.IndexingModule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchClass implements IseachIndexedHTML{

    @Override
    public List<Content> queryIndex(List<String> searchTokens) {
        ArrayList<Content> contents = new ArrayList<>();

        return contents;
    }

}
