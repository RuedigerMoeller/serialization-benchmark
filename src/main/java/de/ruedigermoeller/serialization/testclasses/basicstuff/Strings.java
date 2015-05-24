package de.ruedigermoeller.serialization.testclasses.basicstuff;

import de.ruedigermoeller.serialization.testclasses.HasDescription;

import java.io.Serializable;

/**
 * Created by ruedi on 23/05/15.
 */
public class Strings implements Serializable, HasDescription {

    public Strings() {
    }

    String str[];

    //avoid instance initalizing - actually had no effect on test results ..
    public Strings(int dummy) {
        str = new String[]
            {
                    " License along with this library; if not, write to the Free Software\n" +
                            " * Foundation, Inc., 51 Fradddddnklin Street, Fifth Floor, Boston,\n" +
                            " * MA 02110-1301  USA\n" +
                            " * <p/>",
                    " License along wi§§§rtzrtz§§§th this library; if not, write to the Free Software\n" +
                            " * Foundatâûô4eeöion, Inc., 51 Franklin Street, qweFifth Flooäöüßßßßßßßßßßr, Boston,\n" +
                            " * MA 02110-1qwe301  USA\n" +
                            " * <p/>",
                    " License along wi§§§§§rtz§th this library; if not, write to the Free Software\n" +
                            " * Foundatâûô4eeöion, Inc., 51 Franklin Street, qweFifth Flooäöüßßßßßßßßßßr, Boston,\n" +
                            " * MA 02110-1qwe301  USA\n" +
                            " * <p/>",
                    " License along wi§§§§qwe§§th this library; if not, writqwee to the Free Software\n" +
                            " * Foundatâûôiöon, Inc., 51 Frandfgdfgklin Street, Fifth Flooäöüßßßßßßßßßßr, Boston,\n" +
                            " * MA 02110asaaaaa-1301  USA\n" +
                            " * <p/>",
                    "диноросс Роберт Шлегель предлагает смягчить «антипиратский» закон ко второму чтении, в частности блокировать ссылки с нелегальным видео не по IP, а по URL-адресам. Профильному думскому комитету предстоит выбор между двумя противоположными поправками, предусматривающими распространение закона либо только на кино, либо на все произведения искусства. Эксперты уверены, что новация приведет к снижению потребления легального, а не контрафактного контента." +
                            "В распоряжении «Газеты.Ru» оказалась часть поправок, которыми предлагается дополнить законопроект «О внесении изменений в законодательные акты Российской Федерации по вопросам защиты интеллектуальных прав в информационно-телекоммуникационных сетях», направленный на борьбу с пиратством в Интернете. Депутаты проголосовали за него в первом чтении в минувшую пятницу. Документ был внесен в Госдуму членами думского комитета по культуре (среди них Владимир Бортко и Елена Драпеко) 6 июня. Законопроект принимался при активном лоббировании со стороны Минкульта, хотя Минкомсвязи и представители интернет-индустрии обращали внимание на недоработанность его концепции в целом, о чем ранее подробно рассказывала «Газета.Ru».\n" +
                            "Согласно тексту законопроекта, за размещение «пиратского» контента предусмотрена блокировка сайта интернет-провайдером по IP-адресу, что может привес",
                    " License along wi§§§§qwe§§th this library; if not, writqwee to the Free Software\n" +
                            " * Foundatâûôiöon, Inc., 51 Frandfgdfgklin Street, Fifth Flooäöüßßßßßßßßßßr, Boston,\n" +
                            " * MA 02110asaaaaa-1301  USA\n" +
                            " * <p/>",
            };
    }

    @Override
    public String getDescription() {
        return "measures serialization of mid size to very long Strings";
    }

}
