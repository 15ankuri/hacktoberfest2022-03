package test;

import com.google.common.collect.BiMap;
import main.Huffman.HuffmanTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void shouldRetrieve() {
        String original = "4_Aiv'o'kZY 8_?}?I}xk1?tO3%qV,vCh!B}nYnH:?$(9GG4iQQ@gTAMB^>xx.ay7@,R]9/f|/NCXki;DJUo1P~%x*}h;h1Rvvi}Lp]r>FuV-r=K})fW*,]z2a2J/yh[%4w]?K|# E_4&:yZ651DMSoqUL}KhkD17\\~Q+tm^e4$~``8AF\\]4tX  'JcL4N<tIu-0gaK-6qU&\"7VWBRs#hu$B]3dt{!l}FS7MaT}} >W;;jr=pc9f|lhm>%'QHn`y1&LeGv)hTqgr'PNo\"1CEc(2gUI86Q'c0Xqf[3R+xlO3UG3*=BDI|<#+$9RqNb5?L]Nbyr\\$BO $8(:Ub[~u%FGDLg#(%^0|P.A#(t~;_,a)eyj$Ifgd8*BQkQVkv34A/X1]wZ~(|k59k#bR0L~8,w)c7(~($:YXs4CQ\\mezIFo-D#w=rp={mf}1__z|i:)8@L!~t<}%?_>v_3mbqQ%mLV>GOUPNaN>U%CNF2,,z1E}`%Pu8u[rl!\n";
        BiMap<Character, String> huffmanTable = HuffmanTable.makeTable(original);
        String encoded = main.Codec.Encoder.encode(original, huffmanTable);
        String compressed = main.Util.CompressedFile.compress(encoded);
        String decompressed = main.Util.CompressedFile.decompress(compressed);
        String decoded = main.Codec.Decoder.decode(decompressed, huffmanTable);
        Assertions.assertEquals(encoded, decompressed);
        Assertions.assertEquals(original, decoded);
    }
}