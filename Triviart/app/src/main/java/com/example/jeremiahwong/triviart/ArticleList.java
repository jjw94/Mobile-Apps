package com.example.jeremiahwong.triviart;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleList extends ListFragment{
    public ArrayList<String> articleArray;
    public ArrayList<String> readArray;
    private ItemChangedListener icl;

    public interface ItemChangedListener {
        void onSelectedItemChanged(String articleName);
    }

    public void setItemChangedListener(ItemChangedListener listener) {
        icl = listener;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        articleArray = new ArrayList<String>() {{
            add("Chemistry");
            add("Cryptography");
            add("Data Structures");
            add("Information Sciences");
            add("Physics");
        }};

        readArray = new ArrayList<String>() {{
            add("Ionic bonding involves a transfer of an electron, so one atom gains an electron while one atom loses an electron. One of the resulting ions carries a negative charge (anion), and the other ion carries a positive charge (cation). Because opposite charges attract, the atoms bond together to form a molecule." +
                    "The most common bond in organic molecules, a covalent bond involves the sharing of electrons between two atoms. The pair of shared electrons forms a new orbit that extends around the nuclei of both atoms, producing a molecule.");
            add("Symmetric algorithms such as DES and AES encrypt and decrypt a message using the same key. If you hold a key, you can exchange messages with anybody else holding the same key. It is a shared secret. But be careful who you give the key to. Once it gets in the wrong hands, there is no getting it back. That person can read all of your past messages, and create new messages that are indistinguishable from valid data." +
            "Asymmetric algorithms such as RSA use a different key to encrypt than they do to decrypt. The encrypting key is called the public key and the decrypting key is the private key. If you hold the private key, I can send you a message that only you can read.");
            add("Data Structure is a systematic way to organize data in order to use it efficiently. Following terms are the foundation terms of a data structure. Each data structure has an interface. Interface represents the set of operations that a data structure supports. An interface only provides the list of supported operations, type of parameters they can accept and return type of these operations." +
            "Implementation provides the internal representation of a data structure. Implementation also provides the definition of the algorithms used in the operations of the data structure.");
            add("Looping in programming languages is a feature which facilitates the execution of a set of instructions/functions repeatedly while some condition evaluates to true. A while loop is a control flow statement that allows code to be executed repeatedly based on a given Boolean condition. The while loop can be thought of as a repeating if statement." +
            "A for loop provides a concise way of writing the loop structure. Unlike a while loop, a for statement consumes the initialization, condition and increment/decrement in one line thereby providing a shorter, easy to debug structure of looping.");
            add("Protons and Netrons are held together in the nucleus of an atom by the strong-force. This force acts over a very short distance of about ~1 fm, (10-15m) and over this short distance it can overcome the electromagnetic repulsion between the positively charged protons. Nuclei with radii that are within the range of the Strong force are stable." +
            "As atomic number increases the radius of the nucleus also increases and the element becomes unstable. This instablity manifests itself as the emission of particles or energy from the nucleus. The elements with atomic number greater than 82 are radioactive.");
        }};

        setListAdapter(new ArticleArrayAdapter<String>(getActivity(), R.layout.quizlist, articleArray));
        ListView list = getListView();
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setBackgroundColor(Color.WHITE);
        list.setOnItemClickListener(itemsOnItemClickListener);
    }

    private static class ViewHolder {
        TextView articleTextView;
    }

    private class ArticleArrayAdapter<T> extends ArrayAdapter<String> {
        private Context context;
        private LayoutInflater inflater;
        private List<String> articles;

        public ArticleArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, -1, objects);
            this.context = context;
            this.articles = objects;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.quizlist, null);
                vh = new ViewHolder();
                vh.articleTextView = (TextView) convertView.findViewById(R.id.quiz);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            String article = articles.get(position);
            vh.articleTextView.setText(article);

            return convertView;
        }
    }

    private AdapterView.OnItemClickListener itemsOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            readArray = new ArrayList<String>() {{
                add("Ionic boning involves a transfer of an electron, so one atom gains an electron while one atom loses an electron. One of the resulting ions carries a negative charge (anion), and the other ion carries a positive charge (cation). Because opposite charges attract, the atoms bond together to form a molecule." +
                        "The most common bond in organic molecules, a covalent bond involves the sharing of electrons between two atoms. The pair of shared electrons forms a new orbit that extends around the nuclei of both atoms, producing a molecule.");
                add("Symmetric algorithms such as DES and AES encrypt and decrypt a message using the same key. If you hold a key, you can exchange messages with anybody else holding the same key. It is a shared secret. But be careful who you give the key to. Once it gets in the wrong hands, there is no getting it back. That person can read all of your past messages, and create new messages that are indistinguishable from valid data." +
                        "Asymmetric algorithms such as RSA use a different key to encrypt than they do to decrypt. The encrypting key is called the public key and the decrypting key is the private key.");
                add("Data Structure is a systematic way to organize data in order to use it efficiently. Following terms are the foundation terms of a data structure. Each data structure has an interface. Interface represents the set of operations that a data structure supports. An interface only provides the list of supported operations, type of parameters they can accept and return type of these operations." +
                        "Implementation provides the internal representation of a data structure. Implementation also provides the definition of the algorithms used in the operations of the data structure.");
                add("Looping in programming languages is a feature which facilitates the execution of a set of instructions/functions repeatedly while some condition evaluates to true. A while loop is a control flow statement that allows code to be executed repeatedly based on a given Boolean condition. The while loop can be thought of as a repeating if statement." +
                        "A for loop provides a concise way of writing the loop structure. Unlike a while loop, a for statement consumes the initialization, condition and increment/decrement in one line thereby providing a shorter, easy to debug structure of looping.");
                add("Protons and Netrons are held together in the nucleus of an atom by the strong-force. This force acts over a very short distance of about ~1 fm, (10-15m) and over this short distance it can overcome the electromagnetic repulsion between the positively charged protons. Nuclei with radii that are within the range of the Strong force are stable." +
                        "As atomic number increases the radius of the nucleus also increases and the element becomes unstable. This instablity manifests itself as the emission of particles or energy from the nucleus. The elements with atomic number greater than 82 are radioactive.");
            }};
            icl.onSelectedItemChanged(readArray.get(position));
        }
    };
}
