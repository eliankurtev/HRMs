package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.dto.ApplicationForVacationDto;
import com.hr.hrsystem.service.HireEmployeeService;
import com.hr.hrsystem.service.PdfCreatorService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

@Service
public class PdfCreatorServiceImpl implements PdfCreatorService {

    @Autowired
    HireEmployeeService hireEmployeeService;


    private static final String FONT_PATH = "fonts/Montserrat-Regular.ttf";

    private static final String FIRM_NAME = "ФИРМА ООД, ЕИК 202857047";
    private static final String FIRM_ADDRESS = "гр. ПЕТРИЧ, УЛ. ХРИСТО БОТЕВ No35 ЕТ. 5 АП.12";
    private static final String FIRM_HR_NAME = "ПЕТЪР ПЕТРОВ ПЕТРОВ";
    private static final String FIRM_HR_EGN = "1111111111";

    private static final String EMPLOYEE_NAME = "ГЕОРГИ ГЕОРГИЕВ ГЕОРГИЕВ";
    private static final String EMPLOYEE_EGN = "2222222222";
    private static final String EMPLOYEE_NUMBER_LK = "No 666666666";
    private static final String EMPLOYEE_LK_DATE = "17.05.2005";
    private static final String EMPLOYEE_LK_MVR = "МВР - Ловеч";
    private static final String EMPLOYEE_ADDRESS = "гр. Бургас";
    private static final double EMPLOYEE_SALARY = 10000;

    private static final int BIG_SIZE = 15;
    private static final int SMALL_SIZE = 12;

    private static final String EMPTY_STRING = "";

    private static Font fontBigBold;
    private static Font fontSmallBold;
    private static Font fontSmallNormal;

    static {
        try {
            BaseFont courier = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            fontBigBold = new Font(courier, BIG_SIZE, Font.BOLD);
            fontSmallBold = new Font(courier, SMALL_SIZE, Font.BOLD);
            fontSmallNormal = new Font(courier, SMALL_SIZE, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createApplication(Long id) throws FileNotFoundException, DocumentException {
        ApplicationForVacationDto app = hireEmployeeService.createApplicationForVacation(id);



        Document document = new Document();

        Paragraph forWhom = new Paragraph("До Директора/Управителя\n", fontSmallBold);
        forWhom.add(new Chunk(" на ", fontSmallNormal));
        forWhom.add(FIRM_NAME);
        forWhom.setAlignment(Element.ALIGN_RIGHT);

        Paragraph title = new Paragraph("МОЛБА ЗА ОТПУСК", fontBigBold);
        title.setAlignment(Element.ALIGN_CENTER);

        Paragraph from = new Paragraph("от", fontSmallNormal);
        from.setAlignment(Element.ALIGN_CENTER);

        Paragraph fromWhom = new Paragraph(EMPTY_STRING, fontSmallNormal);
        Chunk employeeName = new Chunk(app.getFirstNameEmployee(), fontSmallBold);
        Chunk position = new Chunk(", на длъжност ", fontSmallNormal);
        Chunk employeePosition = new Chunk("СПЕЦИАЛИСТ СОФТУЕРНО ТЕСТВАНЕ", fontSmallBold);
        fromWhom.add(employeeName);
        fromWhom.add(position);
        fromWhom.add(employeePosition);
        fromWhom.setAlignment(Element.ALIGN_CENTER);

        Paragraph letter = new Paragraph(EMPTY_STRING, fontSmallNormal);
        Chunk startLetter = new Chunk("Уважаеми г-н/г-жо Управител,\n\n" +
                "Моля, да ми бъде разрешено да ползвам платен годишен отпуск на основание чл.155, ал.1 /" +
                "чл.155, ал.5 (и чл.156) от КТ от ");
        Chunk daysOff = new Chunk("1234", fontSmallBold);
        Chunk thatStart = new Chunk(" дни с начало: ", fontSmallNormal);
        Chunk date = new Chunk(LocalDate.now().toString(), fontSmallBold);
        letter.add(startLetter);
        letter.add(daysOff);
        letter.add(thatStart);
        letter.add(date);

        Paragraph today = new Paragraph("Дата: " + LocalDate.now().toString(), fontSmallBold);

        Paragraph bestRegards = new Paragraph("С УВАЖЕНИЕ:", fontSmallBold);

        PdfWriter.getInstance(document, new FileOutputStream("application.pdf"));

        document.open();
        document.add(forWhom);
        document.add(Chunk.NEWLINE);
        document.add(title);
        document.add(Chunk.NEWLINE);
        document.add(from);
        document.add(Chunk.NEWLINE);
        document.add(fromWhom);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(letter);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(today);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(bestRegards);

        document.close();

    }

    public void createContract() throws DocumentException, FileNotFoundException {
        Document document = new Document();

        String trudovDogovor = "ТРУДОВ ДОГОВОР";
        Paragraph trudovDogovorParagraph = new Paragraph(trudovDogovor, fontBigBold);
        trudovDogovorParagraph.setAlignment(Element.ALIGN_CENTER);

        Paragraph intro = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateIntroParagraph(intro, fontSmallBold);

        Chunk firmName = new Chunk(FIRM_NAME, fontSmallBold);
        Paragraph paragraphFirm = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateFirmParagraph(paragraphFirm, fontSmallBold, firmName);

        Paragraph and = new Paragraph("и", fontSmallNormal);
        and.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraphEmployee = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateEmployeeParagraph(paragraphEmployee, fontSmallBold, firmName);


        Paragraph stranite = new Paragraph("(заедно наричани по-долу СТРАНИТЕ).", fontSmallNormal);
        Paragraph sporazumenie = new Paragraph("Предвид предварителните споразумения и уговорките между тях " +
                "Страните по настоящия Договор се споразумяват за следното:", fontSmallNormal);

        Paragraph dlujnostHeader = new Paragraph("1. Длъжност и място на работа", fontSmallBold);
        dlujnostHeader.setAlignment(Element.ALIGN_CENTER);

        Paragraph dlujnostParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateDlujnostParagraph(dlujnostParagraph, fontSmallBold);

        Paragraph vuznagrazhdenieHeader = new Paragraph("2. Възнаграждение", fontSmallBold);
        vuznagrazhdenieHeader.setAlignment(Element.ALIGN_CENTER);
        vuznagrazhdenieHeader.add( Chunk.NEWLINE );
        Paragraph vuznagrazhdenieParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateVuznagrazhdenieParagraph(vuznagrazhdenieParagraph, fontSmallBold);

        Paragraph zaduljeniaHeader = new Paragraph("3. Задължения и изисквания към Страните", fontSmallBold);
        zaduljeniaHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph zaduljeniaParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateZaduljeniaParagraph(zaduljeniaParagraph, fontSmallBold);

        Paragraph rabotnoVremeHeader = new Paragraph("4. Работно време и отпуск", fontSmallBold);
        rabotnoVremeHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph rabotnoVremeParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateRabotnoVremeParagraph(rabotnoVremeParagraph, fontSmallBold);

        Paragraph pridobivkiHeader = new Paragraph("5. Придобивки", fontSmallBold);
        pridobivkiHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph pridobivkiParagraph = new Paragraph("5.1 Служителят има право да участва във всички социални програми на Работодателя, но ще има\n" +
                "възможност да участва в тях по усмотрение на Работодателя.", fontSmallNormal);

        Paragraph konfidencialnostHeader = new Paragraph("6. Конфиденциалност", fontSmallBold);
        konfidencialnostHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph konfidencialnostParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateKonfidencialnostParagraph(konfidencialnostParagraph);

        Paragraph konfliktiHeader = new Paragraph("7. Конфликт на интереси", fontSmallBold);
        konfliktiHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph konfliktiParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateKonfliktiParagraph(konfliktiParagraph);

        Paragraph srokHeader = new Paragraph("8. Срок на договора", fontSmallBold);
        srokHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph srokParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateSrokParagraph(srokParagraph);

        Paragraph srokIzpitvaneHeader = new Paragraph("9. Срок на изпитване", fontSmallBold);
        srokIzpitvaneHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph srokIzpitvaneParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateSrokIzpitvaneParagraph(srokIzpitvaneParagraph);


        Paragraph prekratiavaneHeader = new Paragraph("10. Прекратяване на Договора", fontSmallBold);
        prekratiavaneHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph prekratiavaneParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generatePrekratiavaneParagraph(prekratiavaneParagraph);

        Paragraph izmeneniaHeader = new Paragraph("11. Изменения и допълнения на Договора", fontSmallBold);
        izmeneniaHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph izmeneniaParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateIzmeneniaParagraph(izmeneniaParagraph);

        Paragraph sporoveHeader = new Paragraph("12. Приложимо право. Спорове", fontSmallBold);
        sporoveHeader.setAlignment(Element.ALIGN_CENTER);
        Paragraph sporoveParagraph = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generateSporoveParagraph(sporoveParagraph);

        Paragraph podpisi = new Paragraph("ЗА РАБОТОДАТЕЛЯ:                                    ЗА СЛУЖИТЕЛЯ:                  ", fontSmallBold);
        podpisi.setAlignment(Element.ALIGN_CENTER);

        Paragraph poluchilEkzempliar = new Paragraph(EMPTY_STRING, fontSmallNormal);
        generatePoluchilEkzempliar(poluchilEkzempliar, fontSmallBold);


        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

        document.open();
        document.add(trudovDogovorParagraph);
        document.add( Chunk.NEWLINE );
        document.add(intro);
        document.add( Chunk.NEWLINE );
        document.add(paragraphFirm);
        document.add(and);
        document.add(paragraphEmployee);
        document.add( Chunk.NEWLINE );
        document.add(stranite);
        document.add( Chunk.NEWLINE );
        document.add(sporazumenie);
        document.add( Chunk.NEWLINE );
        document.add(dlujnostHeader);
        document.add( Chunk.NEWLINE );
        document.add(dlujnostParagraph);
        document.add(vuznagrazhdenieHeader);
        document.add( Chunk.NEWLINE );
        document.add(vuznagrazhdenieParagraph);
        document.add(zaduljeniaHeader);
        document.add(Chunk.NEWLINE);
        document.add(zaduljeniaParagraph);
        document.add(Chunk.NEWLINE);
        document.add(rabotnoVremeHeader);
        document.add(rabotnoVremeParagraph);
        document.add(Chunk.NEWLINE);
        document.add(pridobivkiHeader);
        document.add(pridobivkiParagraph);
        document.add(Chunk.NEWLINE);
        document.add(konfidencialnostHeader);
        document.add(konfidencialnostParagraph);
        document.add(Chunk.NEWLINE);
        document.add(konfliktiHeader);
        document.add(konfliktiParagraph);
        document.add(Chunk.NEWLINE);
        document.add(srokHeader);
        document.add(srokParagraph);
        document.add(Chunk.NEWLINE);
        document.add(srokIzpitvaneHeader);
        document.add(srokIzpitvaneParagraph);
        document.add(Chunk.NEWLINE);
        document.add(prekratiavaneHeader);
        document.add(prekratiavaneParagraph);
        document.add(Chunk.NEWLINE);
        document.add(izmeneniaHeader);
        document.add(izmeneniaParagraph);
        document.add(Chunk.NEWLINE);
        document.add(sporoveHeader);
        document.add(sporoveParagraph);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(podpisi);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(poluchilEkzempliar);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(podpisi);
        
        document.close();
    }

    private void generatePoluchilEkzempliar(Paragraph poluchilEkzempliar, Font fontSmallBold) {
        String startText = "С настоящото се удостоверява, че Служителят е започнал работа при Работодателя на ";
        Chunk date = new Chunk(LocalDate.now().toString(),fontSmallBold);
        String endText = " и е " +
                "получил екземпляр от настоящия Договор, както и копие от Уведомлението по чл. 62, ал. 4 от Кодекса на " +
                "труда, заверено от териториалното поделение на Национална агенция по приходите.";
        poluchilEkzempliar.add(startText);
        poluchilEkzempliar.add(date);
        poluchilEkzempliar.add(endText);
    }

    private void generateSporoveParagraph(Paragraph sporoveParagraph) {
        String text = "12.1 Настоящият Договор е изготвен в съответствие с изискванията на законодателството на Република " +
                "България.\n" +
                "12.2 За всички въпроси, нерегулирани от настоящия Договор, се прилагат разпоредбите на действащото в " +
                "Република България трудово и общото гражданско законодателство.\n" +
                "12.3 Всички спорове между страните, възникващи във връзка с настоящия Договор, ще се разрешават по " +
                "взаимното съгласие на страните. В случай, че такова съгласие не може да бъде постигнато, споровете ще " +
                "се решават от компетентния български съд. " +
                "Настоящият Договор бе изготвен и подписан в два екземпляра на български език, за всяка от Страните.";
        sporoveParagraph.add(text);
    }

    private void generateIzmeneniaParagraph(Paragraph izmeneniaParagraph) {
        String text = "11.1 Всички изменения и допълнения на настоящия Договор ще се правят от страните по взаимно " +
                "съгласие, изразено писмено. Работодателят може едностранно да увеличава възнаграждението на " +
                "служителя по този Договор.";
        izmeneniaParagraph.add(text);
    }

    private void generatePrekratiavaneParagraph(Paragraph prekratiavaneParagraph) {
        String text = "10.1 Настоящият Договор може да бъде прекратен на някое от следните основания:\n" +
                "(a) по взаимно съгласие на Страните, изразено писмено;\n" +
                "(б) след изтичане на срока по чл.9 от договора, всяка от Страните може да прекрати договора с " +
                "едномесечно писмено предизвестие на основанията, предвидени в българския Кодекс на труда.\n" +
                "10.2 При едностранно прекратяване на трудовото правоотношение от някоя от страните преди изтичането " +
                "на срока на трудовия договор без спазване на срока на предизвестие, неизправната страна се задължава " +
                "да заплати на изправната страна обезщетение в размер на брутното трудово възнаграждение на " +
                "Служителя за неспазения срок на предизвестието.";
        prekratiavaneParagraph.add(text);
    }

    private void generateSrokIzpitvaneParagraph(Paragraph srokIzpitvaneParagraph) {
        String text = "9.1 На основание чл. 70, ал. 1 от КТ страните се съгласяват, че настоящия договор е със срок за изпитване" +
                "6 месеца в полза на Работодателя, считано от Началната дата на договора.";
        srokIzpitvaneParagraph.add(text);
    }

    private void generateSrokParagraph(Paragraph srokParagraph) {
        String text = "8.1 Настоящият Договор влиза в сила от деня, в който Служителят започне работа при Работодателя " +
                "(\"Начална Дата\"), което се удостоверява писмено от Служителя и Работодателя и има действие за " +
                "неопределен срок от време.";
        srokParagraph.add(text);
    }

    private void generateKonfliktiParagraph(Paragraph konfidencialnostParagraph) {
        String text = "7.1 Служителят се задължава да работи единствено за Работодателя за срока на този договор и няма да " +
                "приема друга позиция на трудов или на граждански договор, без предварителното писмено съгласие на " +
                "Работодателя.";
        konfidencialnostParagraph.add(text);
    }

    private void generateKonfidencialnostParagraph(Paragraph konfidencialnostParagraph) {
        String text = "6.1 От Служителя се изисква да не разкрива каквато и да било информация, търговски тайни, търговска " +
                "или техническа информация, която му е предоставена във връзка с изпълнението и за срока на " +
                "договорните му взаимоотношения с Работодателя.\n" +
                "6.2 Служителят доброволно предоставя личните си данни, в обема в който последните могат да бъдат " +
                "изисквани от Дружеството съгласно действащото българското законодателство, и изрично се съгласява " +
                "същите да бъдат използвани от Работодателя за целите на този договор.";
        konfidencialnostParagraph.add(text);
    }

    private void generateRabotnoVremeParagraph(Paragraph rabotnoVremeParagraph, Font fontSmallBold) {
        String text = "4.1 Работният ден се състои от 8 работни часа, в които не се включват почивките през работно време, " +
                "определени с вътрешна заповед. Работната седмица се състои от 5 работни дни.\n" +
                "4.2 Служителят ползва платен годишен отпуск в размер на 20 работни дни за една година.\n" +
                "4.3 Служителят може да ползва отпуска си по време, договорено с Работодателя.\n";
        rabotnoVremeParagraph.add(text);
    }

    private void generateZaduljeniaParagraph(Paragraph zaduljeniaParagraph, Font fontSmallBold) {
        String threePointOne = "3.1 Служителят се задължава:\n" +
                "(a) да идва навреме на работа;\n" +
                "(б) да се явява на работа в състояние, което му позволява да изпълнява възложените му задачи;\n" +
                "(в) да използва цялото си работно време, внимание и умения за задълженията, поети с настоящия " +
                "Договор и следващи от заеманата длъжност, както и добросъвестно да изпълнява възложените му " +
                "ангажименти;\n" +
                "(г) да изпълнява възложената му работа в изискуемото се количество и качество, както е описано в " +
                "приложената към настоящия договор длъжностна характеристика;\n" +
                "(д) да съблюдава техническите и технологични правила;\n" +
                "(е) да съблюдава правилата за здравословни и безопасни условия на труд;\n" +
                "(ж) да изпълнява законосъобразните нареждания на Работодателя;\n" +
                "(з) да пази грижливо имуществото на Работодателя, което му е поверено във връзка с изпълнение на\n" +
                "задълженията му;\n" +
                "(и) при прекратяване на настоящия Договор да върне на Работодателя всички документи и материали, " +
                "които са му били предоставени от Работодателя във връзка с изпълнението на трудовите му задължения, " +
                "поети с настоящия Договор;\n" +
                "(й) да бъде лоялен към Работодателя, да пази доброто му име, да не злоупотребява с неговото доверие, " +
                "както и да не разпространява поверителни за него сведения, станали му известни в хода на възложената " +
                "му работа или при условията на чл. 6 по-долу;\n" +
                "(к) да спазва вътрешните правила в предприятието на Работодателя, както и да не пречи на останалите " +
                "служители да изпълняват задълженията си;\n" +
                "(л) да съгласува работата си с останалите служители на Работодателя и да им помага при изпълнението на " +
                "поставените им задачи;\n" +
                "(м) да съблюдава работната дисциплина, в съответствие със законодателството и вътрешните правила на " +
                "Работодателя;\n" +
                "(н) да изпълнява други задължения, произтичащи от нормативен акт, настоящия Договор и естеството на " +
                "работата;\n" +
                "(о) в интерес на работата да се стреми да повишава постоянно своята професионална подготовка и " +
                "квалификация;\n" +
                "(п) да не укрива или премълчава от Работодателя обстоятелства или събития, които могат да доведат до " +
                "неблагополучия в работата;\n" +
                "(р) да не обсъжда месечното си възнаграждение пред останалите служители на Работодателя;\n" +
                "(с) да не води в помещенията на Работодателя външни лица без да уведоми предварително " +
                "Работодателя;\n" +
                "(т) да спазва инструкциите и вътрешните правила на Работодателя, както и да се съобразява с политиката " +
                "и процедурите, приети от Работодателя;\n" +
                "(у) във връзка с изпълнението и за срока на действие на договорните му взаимоотношения с " +
                "Работодателя да се съобразява с ограниченията и да изпълнява задълженията си, поети към Дружеството, " +
                "чрез подписаната Декларация за обекти на интелектуална и индустриална собственост - неразделна част " +
                "от този договор. Служителят дължи обезщетение на Дружеството в размер на три брутни месечни " +
                "работни заплати, ако се установи, че е нарушил поетите чрез декларацията по предходното изречение " +
                "задължения към Дружеството;\n" +
                "3.2 За целите на този договор всяко едно от горните задължения ще се счита за съществено, " +
                "нарушаването или неспазването на което ще бъде смятано за тежко нарушение на трудовата дисциплина " +
                "по смисъла на чл. 190, ал. 1, т. 7 от Кодекса на труда.\n" +
                "3.3 Работодателят се задължава:\n" +
                "(a) да осигури съответните здравословни и безопасни условия на труд, необходими на Служителя за " +
                "изпълнението на задълженията му по настоящия Договор;\n" +
                "(б) да предаде на Служителя всички документи и материали, необходими за успешното изпълнение на " +
                "поставените му задачи и задължения по настоящия Договор. Всички такива документи и материали си " +
                "остават собственост на Работодателя;\n" +
                "(в) да прави съответните плащания в полза на Служителя по настоящия Договор;\n" +
                "(г) да прави необходимите данъчни отчисления за данък общ доход, съгласно разпоредбите на " +
                "действащото данъчно законодателство;\n" +
                "(д) да предоставя на Служителя необходимите инструкции за изпълнение на работата по този Договор, " +
                "както и да го запознае с вътрешните правила на Работодателя по отношение на безопасността и " +
                "здравословните условия на работа;";
        zaduljeniaParagraph.add(threePointOne);
        zaduljeniaParagraph.add(Chunk.NEWLINE);
    }

    private void generateVuznagrazhdenieParagraph(Paragraph vuznagrazhdenieParagraph, Font fontSmallBold) {
        String twoPointOneStart = "2.1 Служителят ще получава основно месечно нетно трудово възнаграждение в размер на ";
        Chunk salary = new Chunk(Double.toString(EMPLOYEE_SALARY), fontSmallBold);
        String twoPointOneEnd = " лева.";

        String twoPointTwo = "2.2 Работодателят има право във всеки един момент едностранно да определя и заплаща на Служителя " +
                "допълнителни суми над размера на основното трудово възнаграждение на Служителя с цел максимално " +
                "стимулиране работата на последния и постигане на пълно и реално съответствие на заплащането на " +
                "положените усилия от страна на Служителя в изпълнение настоящия договор.";
        String twoPointThree = "2.3 Работодателят ще прави всички задължителни вноски за фонд \"ПКБ\", ДОО и здравно осигуряване или " +
                "други фондове, в съответствие със задължителните разпоредби на действащото българското " +
                "законодателство.";

        vuznagrazhdenieParagraph.add(twoPointOneStart);
        vuznagrazhdenieParagraph.add(salary);
        vuznagrazhdenieParagraph.add(twoPointOneEnd);
        vuznagrazhdenieParagraph.add(Chunk.NEWLINE);
        vuznagrazhdenieParagraph.add(twoPointTwo);
        vuznagrazhdenieParagraph.add(Chunk.NEWLINE);
        vuznagrazhdenieParagraph.add(twoPointThree);
        vuznagrazhdenieParagraph.add(Chunk.NEWLINE);
    }

    private void generateIntroParagraph(Paragraph intro, Font fontSmallBold) {
        Chunk todayChunk = new Chunk(LocalDate.now().toString(), fontSmallBold);
        intro.setFirstLineIndent(8);
        intro.add("Днес, ");
        intro.add(todayChunk);
        intro.add(" в гр. СОФИЯ, на основание чл. 62 и следващите от Кодекса на труда, се сключи настоящият трудов договор между:");
    }

    private void generateEmployeeParagraph(Paragraph paragraphEmployee, Font fontSmallBold, Chunk firmName) {
        Chunk employeeName = new Chunk(EMPLOYEE_NAME, fontSmallBold);
        Chunk employeeEgn = new Chunk(EMPLOYEE_EGN, fontSmallBold);
        Chunk employeeNumberLK = new Chunk(EMPLOYEE_NUMBER_LK, fontSmallBold);
        Chunk employeeLkDate = new Chunk(EMPLOYEE_LK_DATE, fontSmallBold);
        Chunk employeeLkMvr = new Chunk(EMPLOYEE_LK_MVR, fontSmallBold);
        Chunk employeeAddress = new Chunk(EMPLOYEE_ADDRESS, fontSmallBold);
        Chunk employee = new Chunk("СЛУЖИТЕЛ", fontSmallBold);

        paragraphEmployee.add(employeeName);
        paragraphEmployee.add(", с ЕГН: ");
        paragraphEmployee.add(employeeEgn);
        paragraphEmployee.add(", с лична карта ");
        paragraphEmployee.add(employeeNumberLK);
        paragraphEmployee.add(", изд. на ");
        paragraphEmployee.add(employeeLkDate);
        paragraphEmployee.add(" от ");
        paragraphEmployee.add(employeeLkMvr);
        paragraphEmployee.add(", с адрес: ");
        paragraphEmployee.add(employeeAddress);
        paragraphEmployee.add(", служител в ");
        paragraphEmployee.add(firmName);
        paragraphEmployee.add(", с трудов стаж ... , по-долу ");
        paragraphEmployee.add(employee);

    }

    private void generateFirmParagraph(Paragraph paragraphFirm, Font fontSmallBold, Chunk firmName) {
        Chunk firmAddress = new Chunk(FIRM_ADDRESS, fontSmallBold);
        Chunk firmHrName = new Chunk(FIRM_HR_NAME, fontSmallBold);
        Chunk firmHrEgn = new Chunk(FIRM_HR_EGN, fontSmallBold);
        Chunk firm = new Chunk("РАБОТОДАТЕЛ", fontSmallBold);
        paragraphFirm.add(firmName);
        paragraphFirm.add(" със седалище и адрес на управление: ");
        paragraphFirm.add(firmAddress);
        paragraphFirm.add(" представлявано от ");
        paragraphFirm.add(firmHrName);
        paragraphFirm.add(", с ЕГН: ");
        paragraphFirm.add(firmHrEgn);
        paragraphFirm.add(", по-долу ");
        paragraphFirm.add(firm);
    }

    private void generateDlujnostParagraph(Paragraph dlujnostParagraph, Font fontSmallBold) {

        String onePointOneStart = "1.1 Работодателят назначава Служителя, а Служителят се съгласява да започне работа при " +
                "Работодателя на ";
        Chunk todayChunk = new Chunk(LocalDate.now().toString(), fontSmallBold);
        Chunk dlujnost = new Chunk(" 123456 - СПЕЦИАЛИСТ СОФТУЕРНО ТЕСТВАНЕ ", fontSmallBold);
        String onePointOneEnd = ". Подробно " +
                "описание на изискванията и критериите за заемане на тази позиция се съдържа в длъжностната " +
                "характеристика, одобрена от Работодателя и приложена като неразделна част към настоящия договор.";

        String onePointTwoStart = "1.2 Мястото на работата на Служителя е в ";
        Chunk firmAddress = new Chunk(FIRM_ADDRESS, fontSmallBold);
        String onePointTwoEnd = ", а работното място е в централния офис на Дружеството.";

        String onePointThree = "1.3 Работодателят си запазва правото /при съмнение за злоупотреба/ да следи: трафика на Служителя от " +
                "и към Интернет, начинът на употреба на поверената му техника, както и да съблюдава работните " +
                "помещения, като чрез технически средства за видеонаблюдение или по сходен начин за установяване на " +
                "дистанционна аудиовизуална връзка със Служителя, контролира работния процес.";

        dlujnostParagraph.add(onePointOneStart);
        dlujnostParagraph.add(todayChunk);
        dlujnostParagraph.add(dlujnost);
        dlujnostParagraph.add(onePointOneEnd);
        dlujnostParagraph.add( Chunk.NEWLINE );
        dlujnostParagraph.add(onePointTwoStart);
        dlujnostParagraph.add(firmAddress);
        dlujnostParagraph.add(onePointTwoEnd);
        dlujnostParagraph.add( Chunk.NEWLINE );
        dlujnostParagraph.add(onePointThree);
    }


}
