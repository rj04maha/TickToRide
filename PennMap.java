
import java.util.ArrayList;
import java.util.List;

/**
 * Used to create and handle all the Cities and Routes in Ticket to Ride:
 * Pennsylvania.
 * 
 * Make sure to clean up the commenting around the local variables
 * 
 * @author London Brunell
 */
public class PennMap {

    Route[] routes = new Route[192]; 

    City[] cities = new City[36];
    
    List<DestCard> dest;
    
    public PennMap(){
        //create a list of all the cities
        List citiesList = new ArrayList<>();
        //List citiesList = new ArrayList<>();
        citiesList.add(new City("Albany",1072   ,1093   ,70 ,90));
        citiesList.add(new City("AllenTown",894,    915 ,420    ,440));
        citiesList.add(new City("Altoona", 496  ,516    ,432    ,452));
        citiesList.add(new City("AltanticCity", 1068   ,1089   ,556    ,576));
        citiesList.add(new City("Baltimore", 793    ,814    ,604    ,624));
        citiesList.add(new City("Binghamton", 850   ,870    ,152    ,173));
        citiesList.add(new City("Buffalo", 455  ,475    ,44 ,64));
        citiesList.add(new City("Chambersburg", 593 ,613    ,539    ,560));
        citiesList.add(new City("Coundersport", 551 ,571    ,206    ,226));
        citiesList.add(new City("Cumberland", 498   ,518    ,602    ,622));
        citiesList.add(new City("Dubois",444,467, 335,360 ));
        citiesList.add(new City("Elmira", 704,  724 ,155,   175));
        citiesList.add(new City("Erie",267, 287,    152 ,172));
        citiesList.add(new City("Gettysburg",660    ,680    ,563    ,585));
        citiesList.add(new City("Harrisburg", 702   ,722    ,474    ,494));
        citiesList.add(new City("Johnstown", 440    ,460    ,470    ,490));
        citiesList.add(new City("Lancaster", 787    ,809,   520,    540));
        citiesList.add(new City("Lewiston", 610 ,630    ,421    ,442));
        citiesList.add(new City("MorganTown",284    ,304    ,607    ,627));
        citiesList.add(new City("NewYork",1073 ,1093   ,316    ,338 ));
        citiesList.add(new City("OilCity",315  ,336    ,280    ,302));
        citiesList.add(new City("Ontario",200   ,222    ,40 ,61)); 
        citiesList.add(new City("Ontario", 372  ,394,   42, 63));
        citiesList.add(new City("Philadelphia", 958 ,979    ,543    ,565));
        citiesList.add(new City("Pittsburgh",271    ,291    ,453    ,474));  
        citiesList.add(new City("Reading", 806  ,826    ,458    ,480));   
        citiesList.add(new City("Rochester",660,    681 ,43 ,63));   
        citiesList.add(new City("Scranton/Wikes", 876   ,897    ,284    ,304));   
        citiesList.add(new City("Stroudsburg", 962  ,983    ,354    ,374));   
        citiesList.add(new City("Syracuse", 831,    851,    44  ,64));
        citiesList.add(new City("Towanda",763,  783 ,221    ,241));
        citiesList.add(new City("Warren", 389   ,409    ,204    ,225));
        citiesList.add(new City("Wheeling",188  ,208    ,517    ,537));
        citiesList.add(new City("Williamsport", 681,    701 ,310    ,330));
        citiesList.add(new City("York", 728,    748,    536 ,557));
        citiesList.add(new City("Youngstown", 181   ,202    ,311    ,332));

        cities = (City[])citiesList.toArray(new City[]{});

        //create a list of all the Routes
        List routesList = new ArrayList<>();

        //Albany
        // to Syracuse
        routesList.add(new Route(cities[0],cities[29],TrainColor.WHITE,6,new String[]{"New York Central System"},new int[]{859,1061,1061,858},new int[]{42,62,73,53}));
        routesList.add(new Route(cities[0],cities[29],TrainColor.RED,6,new String[]{"New York Central System"}, new int[]{858,1061,1060,857} ,  new int[]{53,73,85,65}));
        // to Binghamton
        routesList.add(new Route(cities[0],cities[5], TrainColor.PINK, 6, new String[]{}, new int[]{875,1065,1070,879}, new int[]{157,1065,96,173}));
        // to NY
        routesList.add(new Route(cities[0],cities[19], TrainColor.BLUE, 6, new String[]{"Pennsylvania","Baltimore","New York Central System"},new int[] {1068,1077,1077, 1068}  ,new int[] {103,103,308, 308}));
        routesList.add(new Route(cities[0],cities[19], TrainColor.GREEN,6, new String[]{"Pennsylvania", "Baltimore", "New York Central System"},new int[] {1080, 1091,  1091,1080}  ,new int[] {103, 103, 309,309  }));
        
        //Allentown 
        // to Stroudsburg
        routesList.add(new Route(cities[1],cities[28],TrainColor.ORANGE ,2  , new String[]{"Reading Railroad","Lehigh Valley","Jersey Central Line" },new int[] {913, 961, 969, 921 }, new int[] {419, 372, 380,427 }));
        // to Scranton
        routesList.add(new Route(cities[1],cities[27],TrainColor.BLUE , 3 , new String[]{"Pennsylvania", "Reading Railroad", "Lehigh Valley", "Jersey Central Line"},new int[] {886, 884, 888, 898, 908,899, 896,898 } ,new int[] {313, 344, 380, 414, 413, 381, 348, 315}));
        routesList.add(new Route(cities[1],cities[27],TrainColor.WHITE , 3 , new String[]{"Pennsylvania", "Reading Railroad", "Lehigh Valley", "Jersey Central Line"},new int[] {873, 872, 876, 884,898,888,884,886  }, new int[] {314, 347, 382, 417, 414, 380,344,313}));
        // to Reading
        routesList.add(new Route(cities[1],cities[25],TrainColor.GREEN , 2 , new String[]{"Reading Railroad"},new int[] {825, 887, 892, 830},new int[] {454, 432, 442, 464}));
        // to Philadelphia   
        routesList.add(new Route(cities[1],cities[23],TrainColor.BLACK , 3 , new String[]{"Pennsylvania", "Reading Railroad"},new int[] {913, 958, 967, 922},new int[] {442, 531, 527, 438}));
        routesList.add(new Route(cities[1],cities[23],TrainColor.RED , 3 , new String[]{"Pennsylvania", "Reading Railroad"},new int[] {902, 911, 957, 947},new int[] {447, 443, 532, 537}));
        
        //Altoona 
        // to Harrisburg
        routesList.add(new Route(cities[2],cities[14],TrainColor.ORANGE ,5 , new String[]{"Pennslyvania"}, new int[] {528,561, 593,626, 691, 691, 624, 590, 556, 525 },new int[] {437, 451, 461, 468, 477, 486, 482, 471, 460, 446 }));
        routesList.add(new Route(cities[2],cities[14],TrainColor.RED ,5 , new String[]{"Pennsylvania"},new int[] {524, 558, 589, 622, 659, 691, 689, 622, 552, 520},new int[] {499, 463, 473, 482, 489, 492, 502, 495, 473, 460}));
        // to Lewiston
        routesList.add(new Route(cities[2],cities[17],TrainColor.GREEN ,2 , new String[]{},new int[]{531, 597, 597, 531}, new int[]{422, 422, 432, 432} ));
        // to Dubois
        routesList.add(new Route(cities[2],cities[10],TrainColor.WILD , 2 , new String[]{"Pennsylvania"}, new int[]{ 475, 504, 495, 466}, new int[]{360, 421, 425, 365 }));
        // to Johnstown
        routesList.add(new Route(cities[2],cities[15],TrainColor.YELLOW ,1 , new String[]{"Pennsylvania"}, new int[]{457, 485, 489, 464 }, new int[]{458, 443, 453, 470 }));
        routesList.add(new Route(cities[2],cities[15],TrainColor.BLUE ,1 , new String[]{"Pennsylvania"}, new int[]{ 464, 489, 497, 469}, new int[]{ 470, 453, 463, 477}));
        
        //Atlantic City 
        //  to New York
        routesList.add(new Route(cities[3],cities[19],TrainColor.BLACK ,6 , new String[]{"Jersey Central Line"}, new int[]{1068, 1078, 1078, 1068 }, new int[]{ 344, 344, 550, 550}));
        routesList.add(new Route(cities[3],cities[19],TrainColor.WHITE ,6 , new String[]{"Jersey Central Line"}, new int[]{ 1079, 1090, 1090, 1079}, new int[]{ 344, 344, 550, 550}));
        // to Philadelphia 
        routesList.add(new Route(cities[3],cities[23],TrainColor.WILD ,2  , new String[]{"Pennslyvania","Reading Railroad","Jersey Central Line"}, new int[]{992, 1058, 1057, 990 }, new int[]{544, 553, 562, 555 }));
        routesList.add(new Route(cities[3],cities[23],TrainColor.WILD , 2 , new String[]{"Pennslyvania","Reading Railroad","Jersey Central Line"}, new int[]{992, 1058, 1056, 989 }, new int[]{555, 562, 576, 568 }));
        //Baltimore 
        // to Philadelphia 
        routesList.add(new Route(cities[4],cities[23],TrainColor.YELLOW ,4  , new String[]{"Pennslyvania","Baltimore" }, new int[]{819, 852, 886, 918, 949, 953,922, 853, 819}, new int[] {601, 598, 590, 579, 565,574, 601, 608, 612}));
        routesList.add(new Route(cities[4],cities[23],TrainColor.PINK , 4 , new String[]{"Pennslyvania","Baltimore"}, new int[]{822, 855, 888, 922, 951, 955, 927, 894, 858, 822,  }, new int[]{612, 610, 602, 592, 578, 587, 601, 613, 621, 624 }));
        // to York
        routesList.add(new Route(cities[4],cities[34],TrainColor.WHITE ,2  , new String[]{"Pennsylvania","Western Maryland"}, new int[]{ 749, 798, 791, 743}, new int[]{ 551, 597, 605, 560}));
        // to Gettysburg
        routesList.add(new Route(cities[4],cities[13],TrainColor.RED , 3 , new String[]{"Pennslyvania","Western Maryland"}, new int[]{689, 783, 781, 687 }, new int[]{572, 605, 615, 582 }));
        // TO Cumberland
        routesList.add(new Route(cities[4],cities[9],TrainColor.BLUE , 7 , new String[]{"Baltimore", "Pennsylvania"}, new int[]{530, 768, 768, 531 }, new int[]{612, 612, 622, 623 }));
        
        //Binghamton
        // to Albany
        routesList.add(new Route(cities[5],cities[0],TrainColor.PINK ,6  , new String[]{}, new int[]{ 875, 1065, 1070, 879}, new int[]{ 157,1065,96,173}));
        // to Syracuse
        routesList.add(new Route(cities[5],cities[29],TrainColor.YELLOW , 2 , new String[]{"Erie Lackawanna"}, new int[]{ 841, 850, 861, 850}, new int[]{ 81, 79, 145, 146}));
        routesList.add(new Route(cities[5],cities[29],TrainColor.ORANGE , 2 , new String[]{"Erie Lackawanna"}, new int[]{850, 861, 872, 863 }, new int[]{ 79, 77, 142, 144}));
        // to Elmira
        routesList.add(new Route(cities[5],cities[11],TrainColor.WHITE ,3  , new String[]{"Erie Lackawanna"}, new int[]{741, 841, 841, 741 }, new int[]{158, 158, 168, 168 }));
        // to Towanda 
        routesList.add(new Route(cities[5],cities[30],TrainColor.RED , 2 , new String[]{"Erie Lackawanna"}, new int[]{787, 839, 847,791  }, new int[]{212, 174, 183, 221 }));
        // to Scranton
        routesList.add(new Route(cities[5],cities[27],TrainColor.GREEN ,3  , new String[]{"Erie Lackawanna"}, new int[]{856, 866, 884, 875 }, new int[]{179, 178, 276, 278 }));
        routesList.add(new Route(cities[5],cities[27],TrainColor.BLACK ,3  , new String[]{"Erie Lackwanna"}, new int[]{869, 880, 898, 887 }, new int[]{177, 176, 274, 276 }));
        //Buffalo
        //'' to Rochester
        routesList.add(new Route(cities[6],cities[26],TrainColor.BLACK ,5  , new String[]{"Baltimore"}, new int[]{484,653,653,484 }, new int[]{56,56,66,66 }));
        routesList.add(new Route(cities[6],cities[26],TrainColor.YELLOW ,5  , new String[]{"Baltimore"}, new int[]{ 484 ,653,653,484}, new int[]{ 43,43,53,53}));
        //'' to Ontario (Right)
        routesList.add(new Route(cities[6],cities[22],TrainColor.WILD, 1 , new String[]{"Pennsylvania", "Erie Lackawanna", "New York Central System"}, new int[]{ 399,432,432,399}, new int[]{ 41,41,51,51}));
        //routesList.add(new Route(cities[6],cities[22],TrainColor.WILD , 1 , new String[]{"Pennsylvania", "Erie Lackawanna", "New York Central System"}, new int[]{ }, new int[]{ }));
        //'' to Erie
        routesList.add(new Route(cities[6],cities[12],TrainColor.ORANGE , 5 , new String[]{"Erie Lackawanna", "New York Central System"}, new int[]{444,418,389,359,328,296,299,331,364,395,424,450 }, new int[]{57,80,98,115,128,139,149,138,124,107,87,66 }));
        routesList.add(new Route(cities[6],cities[12],TrainColor.WHITE , 5 , new String[]{"Erie Lackwanna", "New York Central System"}, new int[]{ 451,425,396,365,332,300,304,337,369,402,433,458}, new int[]{ 67,89,108,124,140,151,162,150,136,118,97,76}));
        //'' to Warren
        routesList.add(new Route(cities[6],cities[31],TrainColor.GREEN , 4 , new String[]{"Pennsylvania", "Erie Lackawanna", "Baltimore", "New York Central System", "BRP Railway"}, new int[]{ 465,404,414,476}, new int[]{ 76,197,202,87}));
        //'' to CouderSport
        routesList.add(new Route(cities[6],cities[8],TrainColor.WILD ,4  , new String[]{"Pennsylvania", "Erie Lackawanna"}, new int[]{ 480,546,557,491}, new int[]{ 83,204,198,77}));
        //Chambersburg
        //'' to Harrisburg
        routesList.add(new Route(cities[7],cities[14],TrainColor.BLUE ,2  , new String[]{"Western Maryland", "Pennsylvania", "Reading Railroad"}, new int[]{ 617,678,682,621}, new int[]{ 553,505,515,543}));
        //'' to Cumberland
        routesList.add(new Route(cities[7],cities[9],TrainColor.GREEN , 2 , new String[]{"Western Maryland", "Pennsylvania"}, new int[]{ 589,582,528,538}, new int[]{ 562,553,593,602}));
        //'' to Gettysburg
        routesList.add(new Route(cities[7],cities[13],TrainColor.BLACK , 1 , new String[]{}, new int[]{ 625,656,652,621}, new int[]{ 550,562,572,561}));
        
        //Coudersport
        //'' to Elmira
        routesList.add(new Route(cities[8],cities[11],TrainColor.ORANGE ,4  , new String[]{"Pennsylvania", "Erie Lackawanna", "Baltimore", "New York Central System","BRP Railway"}, new int[]{ 572,701,705,576}, new int[]{ 209,164,174,220}));
        //'' to Buffalo
        routesList.add(new Route(cities[8],cities[6],TrainColor.WILD ,4  , new String[]{"Pennsylvania", "Erie Lackawanna"}, new int[]{ 547,480,491,557}, new int[]{ 203,83,77,198}));
        //'' to Warren
        routesList.add(new Route(cities[8],cities[31],TrainColor.WILD,4  , new String[]{"Pennsylvania", "Erie Lackawanna", "Baltimore", "New York Central System","BRP Railway"}, new int[]{ 550,516,580,444,411,413,446,479,514,547}, new int[]{ 226,232,234,232,225,213,220,222,220,214}));
        //'' to Williamsport
        routesList.add(new Route(cities[8],cities[33],TrainColor.GREEN , 4 , new String[]{"Pennsylvania"}, new int[]{ 575,568,578,585}, new int[]{ 225,234,314,305}));
        
        //Cumberland 
        //'' to Chambersburg
        routesList.add(new Route(cities[9],cities[7],TrainColor.GREEN ,2  , new String[]{"Western Maryland","Pennsylvania"}, new int[]{ 528,582,589,535}, new int[]{ 593,553,563,603}));
        //'' to Johnstown
        routesList.add(new Route(cities[9],cities[15],TrainColor.WILD , 3 , new String[]{"Western Maryland", "Pennsylvania", "Baltimore"}, new int[]{ 500,452,463,510}, new int[]{ 591,500,495,586}));
        //'' to Morgantown
        routesList.add(new Route(cities[9],cities[18],TrainColor.RED ,5  , new String[]{"Western Maryland","Baltimore"}, new int[]{ 487,316,316,487}, new int[]{ 624,623,612,612}));
        //'' to Baltimore
        routesList.add(new Route(cities[9],cities[4],TrainColor.BLUE ,7  , new String[]{"Western Maryland","Baltimore"}, new int[]{ 530,530,769,769}, new int[]{ 612,623,624,613}));
       
        //Dubois
        //'' to Williamsport
        routesList.add(new Route(cities[10],cities[33],TrainColor.WHITE , 6 , new String[]{}, new int[]{ 473,474,679,677}, new int[]{ 340,350,329,318}));
        //'' to Warren
        routesList.add(new Route(cities[10],cities[31],TrainColor.BLACK , 3 , new String[]{"BRP Railway", "Erie Lackawanna"}, new int[]{ 448,404,414,458}, new int[]{ 329,236,232,324}));
        //'' to Oil City
        //routesList.add(new Route(cities[10],cities[20],TrainColor.PINK , 3 , new String[]{}, new int[]{ 436,342,345,441}, new int[]{ 347,309,298,336}));
        routesList.add(new Route(cities[10],cities[20],TrainColor.PINK , 3 , new String[]{}, new int[]{ 442,345,441,436}, new int[]{ 309,298,339,347}));
        //'' to Altoona
        routesList.add(new Route(cities[10], cities[2], TrainColor.WILD, 2, new String[]{}, new int[]{465,476,504,494}, new int[]{356,359,420,426}));

        //Elmira
        //'' to Binghamton
        routesList.add(new Route(cities[11],cities[5],TrainColor.WHITE , 3 , new String[]{"Erie Lackawanna"}, new int[]{720, 827, 834, 160}, new int[]{ 158, 158, 169, 169}));
        //'' to Syracuse
        routesList.add(new Route(cities[11],cities[29],TrainColor.BLACK , 4 , new String[]{"Erie Lackawanna","Lehigh Valley"}, new int[]{720, 827, 834, 160 }, new int[]{152, 69, 77, 160 }));
        //'' to Rochester
        routesList.add(new Route(cities[11],cities[26],TrainColor.GREEN , 3 , new String[]{"Pennsylvania","Erie Lackawanna","Baltimore","Lehigh Valley","New York Central System","BRP RailWay"}, new int[]{663, 673, 667, 680, 708, 702, 674, 658 }, new int[]{ 64, 67, 98, 129, 148, 158,141, 104}));
        //'' to Coundersport
        routesList.add(new Route(cities[11],cities[8],TrainColor.ORANGE ,  4, new String[]{"Pennsylvania","Erie Lackawanna","Baltimore","New York Central System","BRP RailWay"}, new int[]{ 574, 699, 703, 577 }, new int[]{209, 165, 174, 219 }));
        //'' to Towanda 
        routesList.add(new Route(cities[11],cities[30],TrainColor.YELLOW ,  2, new String[]{"Pennsylvania","Erie Lackawanna","Lehigh Valley"}, new int[]{713, 723, 732, 761, 757,727,721 }, new int[]{ 179, 176, 207,223, 231,218, 208}));
        //Erie
        //'' to Buffalo
        routesList.add(new Route(cities[12],cities[6],TrainColor.ORANGE , 5 , new String[]{"Erie Lackawanna","New York Central System"}, new int[]{297, 387, 443, 450, 396, 332, 300 }, new int[]{ 139, 98, 57, 65, 106, 138, 149}));
        //'' to Buffalo
        routesList.add(new Route(cities[12],cities[6],TrainColor.YELLOW ,5  , new String[]{"Erie Lackawanna","New York Central System"}, new int[]{ 300, 332, 396, 450, 76, 403, 370, 305}, new int[]{149, 138, 106, 65, 76, 117, 135, 161 }));
        //'' to Ontario Right
        //routesList.add(new Route(cities[12],cities[22],TrainColor.WILD , 1 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{ }, new int[]{ }));
        //routesList.add(new Route(cities[12],cities[22],TrainColor.WILD ,1  , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{ }, new int[]{ }));
        //'' to Ontario Left
        routesList.add(new Route(cities[12],cities[21],TrainColor.WILD , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{218, 229, 281, 272 }, new int[]{ 63, 58, 143, 149}));
        routesList.add(new Route(cities[12],cities[21],TrainColor.WILD , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{210, 218, 272,260 }, new int[]{70, 64, 143, 155 }));
        //'' to Youngstown
        routesList.add(new Route(cities[12],cities[35],TrainColor.GREEN ,4  , new String[]{"Erie Lackawanna","New York Central System"}, new int[]{264, 274, 213, 203 }, new int[]{ 186, 191, 311,307}));
        routesList.add(new Route(cities[12],cities[35],TrainColor.YELLOW , 4 , new String[]{"Erie Lackawanna","New York Central System"}, new int[]{254, 264, 203, 192 }, new int[]{181, 186, 307, 301 }));
        //'' to Oil City
        routesList.add(new Route(cities[12],cities[20],TrainColor.BLACK , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{279, 289, 323, 313 }, new int[]{ 180, 177, 272,275}));
        //'' to Warren 
        routesList.add(new Route(cities[12],cities[31],TrainColor.BLUE , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{292, 296, 389, 387 }, new int[]{174, 163, 200, 211 }));
        
        //Gettysburg 
        routesList.add(new Route(cities[13],cities[34],TrainColor.WILD,1  , new String[]{"Pennsylvania","Western Maryland"}, new int[]{ 691, 721, 724, 695}, new int[]{558, 546,556,567 }));
        routesList.add(new Route(cities[13],cities[14],TrainColor.YELLOW , 2 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{668, 700, 708,680 }, new int[]{558,500,502,562 }));
        routesList.add(new Route(cities[13],cities[7],TrainColor.BLACK , 1 , new String[]{}, new int[]{626, 654, 653, 622 }, new int[]{551, 561, 570, 560 }));
        routesList.add(new Route(cities[13],cities[4],TrainColor.RED ,3  , new String[]{"Pennsylvania","Western Maryland"}, new int[]{689, 785, 781, 686 }, new int[]{572, 606, 615, 582 }));
        
        //Harrisburg
        routesList.add(new Route(cities[14],cities[25],TrainColor.PINK ,2  , new String[]{"Reading Railroad"}, new int[]{735, 801, 801, 734, }, new int[]{469, 462, 473, 478 }));
        routesList.add(new Route(cities[14],cities[27],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{ 722, 861, 869, 731}, new int[]{ 462, 315, 320, 470}));
        routesList.add(new Route(cities[14],cities[17],TrainColor.WILD , 2 , new String[]{"Pennsylvania"}, new int[]{647, 704, 699, 642 }, new int[]{431, 464, 473, 442 }));
        routesList.add(new Route(cities[14],cities[2],TrainColor.RED , 5 , new String[]{"Pennsylvania"}, new int[]{524, 558, 589, 622, 659, 691, 689, 622, 552, 520 }, new int[]{499, 463, 473, 482, 489, 492, 502, 495, 473, 460 }));
        routesList.add(new Route(cities[14],cities[2],TrainColor.ORANGE ,5  , new String[]{"Pennsylvania"}, new int[]{528,561, 593,626, 691, 691, 624, 590, 556, 525  }, new int[]{437, 451, 461, 468, 477, 486, 482, 471, 460, 446  }));
        routesList.add(new Route(cities[14],cities[7],TrainColor.BLUE ,2  , new String[]{"Western Maryland","Pennsylvania","Reading Railroad"}, new int[]{617, 678, 682, 621 }, new int[]{ 553, 505, 515, 543}));
        routesList.add(new Route(cities[14],cities[13],TrainColor.YELLOW , 2 , new String[]{"Pennslyvania","Reading Railroad"}, new int[]{668, 700, 708,680 }, new int[]{558,500,502,562 }));
        routesList.add(new Route(cities[14],cities[34],TrainColor.BLACK , 1 , new String[]{"Pennslyvania","Western Maryland"}, new int[]{711, 720, 734, 724 }, new int[]{ 509, 505, 534,538}));
        routesList.add(new Route(cities[14],cities[16],TrainColor.WILD , 2 , new String[]{"Pennslyvania"}, new int[]{732, 793, 788, 729 }, new int[]{482, 511, 519,489 }));
        routesList.add(new Route(cities[14],cities[16],TrainColor.WILD , 2 , new String[]{"Pennslyvania"}, new int[]{ 729, 788, 782, 724}, new int[]{489, 519, 531, 501 }));
        
        //Johnstown
        routesList.add(new Route(cities[15],cities[2],TrainColor.BLUE , 1 , new String[]{"Pennslyvania"}, new int[]{464, 489, 497, 469 }, new int[]{470, 453, 463, 477 }));
        routesList.add(new Route(cities[15],cities[2],TrainColor.YELLOW ,1  , new String[]{"Pennslyvania"}, new int[]{457, 485, 489, 464 }, new int[]{458, 443, 453, 470 }));
        routesList.add(new Route(cities[15],cities[24],TrainColor.PINK , 4 , new String[]{"Pennslyvania","Baltimore"}, new int[]{301, 362, 433, 434, 365, 296 }, new int[]{452, 465, 465, 476, 476, 462 }));
        routesList.add(new Route(cities[15],cities[24],TrainColor.BLACK , 4 , new String[]{"Pennslyvania","Baltimore"}, new int[]{299, 365, 434, 432, 365, 295 }, new int[]{ 452, 476, 475, 488, 491, 474}));
        routesList.add(new Route(cities[15],cities[9],TrainColor.WILD , 3 , new String[]{"Western Maryland","Pennslyvania","Baltimore"}, new int[]{500, 452, 463, 510 }, new int[]{591,500,495,586 }));
        
        //Lancaster
        routesList.add(new Route(cities[16],cities[25],TrainColor.YELLOW , 1 , new String[]{"Reading Railroad"}, new int[]{ 799, 807, 818, 810}, new int[]{513, 482, 484, 516 }));
        routesList.add(new Route(cities[16],cities[14],TrainColor.WILD , 2 , new String[]{"Pennsylvania"}, new int[]{732, 793, 788, 729 }, new int[]{482, 511, 519,489 }));
        routesList.add(new Route(cities[16],cities[14],TrainColor.WILD , 2 , new String[]{"Pennsylvania"}, new int[]{729, 788, 782, 724 }, new int[]{489, 519, 531, 501 }));
        routesList.add(new Route(cities[16],cities[34],TrainColor.PINK ,1  , new String[]{"Pennsylvania"}, new int[]{ 753, 785, 786, 755}, new int[]{ 540, 535, 546, 550}));
        routesList.add(new Route(cities[16],cities[23],TrainColor.GREEN , 4 , new String[]{"Pennsylvania"}, new int[]{ 812, 877, 945, 946, 878, 809}, new int[]{524, 541, 541, 551, 551, 534 }));
        routesList.add(new Route(cities[16],cities[23],TrainColor.ORANGE , 4 , new String[]{"Pennsylvania"}, new int[]{809, 877, 944, 942, 876, 808 }, new int[]{ 534, 551, 551, 564, 564, 547}));
        
        //Lewiston
        routesList.add(new Route(cities[17],cities[33],TrainColor.YELLOW , 3 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{625, 682, 692, 632 }, new int[]{415, 336, 340, 422 }));
        routesList.add(new Route(cities[17],cities[2],TrainColor.GREEN , 2 , new String[]{}, new int[]{ 531, 597, 597, 531}, new int[]{ 422, 422, 432, 432}));
        routesList.add(new Route(cities[17],cities[14],TrainColor.WILD , 2 , new String[]{"Pennsylvania"}, new int[]{647, 704, 699, 642 }, new int[]{431, 464, 473, 442 }));
        //routesList.add(new Route(cities[17],cities[14],TrainColor.ORANGE , 2 , new String[]{"Pennsylvania"}, new int[]{ }, new int[]{ }));
        
        //MorganTown
        routesList.add(new Route(cities[18],cities[9],TrainColor.RED , 5 , new String[]{"Western Maryland","Baltimore"}, new int[]{316, 487, 487, 317 }, new int[]{613, 613, 623, 623 }));
        routesList.add(new Route(cities[18],cities[24],TrainColor.YELLOW , 3 , new String[]{}, new int[]{277, 286, 296,285  }, new int[]{490, 490, 590, 591 }));
        routesList.add(new Route(cities[18],cities[32],TrainColor.BLUE , 3 , new String[]{"Baltimore"}, new int[]{209, 225, 249, 279, 276, 246, 218, 199 }, new int[]{540, 570, 592, 605,615, 604, 579, 545 }));
        
        //New York
        routesList.add(new Route(cities[19],cities[0],TrainColor.GREEN , 6 , new String[]{"Pennsylvania","Baltimore","New York Central System"}, new int[]{ 1080, 1091,  1091,1080}, new int[]{103, 103, 309,309   }));
        routesList.add(new Route(cities[19],cities[0],TrainColor.BLUE , 6 , new String[]{"Pennsylvania","Baltimore","New York Central System"}, new int[]{1068,1077,1077, 1068 }, new int[]{ 103,103,308, 308 }));
        routesList.add(new Route(cities[19],cities[27],TrainColor.RED  , 5 , new String[]{"Erie Lackawanna","Lehigh Valley","Baltimore","Jersey Central Line"}, new int[]{ 900, 967, 1003, 1073, 1068, 1004, 969, 900 }, new int[]{282, 284, 291, 312, 324,303, 297, 293  }));
        routesList.add(new Route(cities[19],cities[27],TrainColor.PINK , 5 , new String[]{"Erie Lackawanna","Lehigh Valley","Baltimore","Jersey Central Line"}, new int[]{900, 969, 1004, 1068, 1066, 999, 899  }, new int[]{ 293, 297, 303, 324, 335, 317, 308}));
        routesList.add(new Route(cities[19],cities[23],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Baltimore","Reading Railroad","Jersey Central Line"}, new int[]{972, 1041, 1051, 983 }, new int[]{528, 336, 339, 531 }));
        routesList.add(new Route(cities[19],cities[23],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Baltimore","Reading Railroad","Jersey Central Line"}, new int[]{983, 1051, 1065, 535 }, new int[]{528, 339, 345, 536 }));
        routesList.add(new Route(cities[19],cities[3],TrainColor.BLACK , 6 , new String[]{}, new int[]{1068, 1078, 1078, 1068 }, new int[]{344, 344, 550, 550 }));
        routesList.add(new Route(cities[19],cities[3],TrainColor.WHITE ,6  , new String[]{ }, new int[]{1079, 1090, 1090, 1079}, new int[]{344, 344, 550, 550}));
        
        //Oil City
        routesList.add(new Route(cities[20],cities[31],TrainColor.ORANGE , 2 , new String[]{"Pennsylvania","Baltimore","Erie Lackawanna"}, new int[]{ 338, 384, 393, 346}, new int[]{272, 224, 232, 280 }));
        routesList.add(new Route(cities[20],cities[12],TrainColor.BLACK , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{279, 289, 323, 313 }, new int[]{180, 177, 272,275 }));
        routesList.add(new Route(cities[20],cities[35],TrainColor.WHITE ,3  , new String[]{"Erie Lackawanna"}, new int[]{ 206, 303, 305, 207, }, new int[]{ 318, 292, 301, 328}));
        routesList.add(new Route(cities[20],cities[24],TrainColor.RED , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{290, 314, 324, 300 }, new int[]{439, 306, 308, 441 }));
        routesList.add(new Route(cities[20],cities[10],TrainColor.PINK , 3 , new String[]{}, new int[]{436,342,345,441 }, new int[]{347,309,298,336 }));
        
        //Ontario (Left)
        routesList.add(new Route(cities[21],cities[12],TrainColor.WILD ,3  , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{218, 229, 281, 272 }, new int[]{63, 58, 143, 149 }));
        routesList.add(new Route(cities[21],cities[12],TrainColor.WILD , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{210, 218, 272,260 }, new int[]{70, 64, 143, 155 }));
        
        //Ontario (Right)
        routesList.add(new Route(cities[22],cities[6],TrainColor.WILD , 1 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{400, 431, 433, 399 }, new int[]{41, 41, 51, 51 }));
        routesList.add(new Route(cities[22],cities[6],TrainColor.WILD ,1  , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{400, 431, 433, 399 }, new int[]{51, 51, 64, 64 }));
        
        //Philadelphia
        routesList.add(new Route(cities[23],cities[19],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Baltimore","Reading Railroad","Jersey Central Line"}, new int[]{972, 1041, 1051, 983 }, new int[]{528, 336, 339, 531 }));
        routesList.add(new Route(cities[23],cities[19],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Baltimore","Reading Railroad","Jersey Central Line"}, new int[]{ 983, 1051, 1065, 535}, new int[]{528, 339, 345, 536 }));
        
        routesList.add(new Route(cities[23],cities[1],TrainColor.RED , 3 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{  902, 911, 957, 947}, new int[]{447, 443, 532, 537 }));
        routesList.add(new Route(cities[23],cities[1],TrainColor.BLACK , 3 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{913, 958, 967, 922 }, new int[]{442, 531, 527, 438 }));
        
        routesList.add(new Route(cities[23],cities[16],TrainColor.ORANGE , 4 , new String[]{"Pennsylvania"}, new int[]{809, 877, 944, 942, 876, 808 }, new int[]{534, 551, 551, 564, 564, 547 }));
        routesList.add(new Route(cities[23],cities[16],TrainColor.GREEN , 4 , new String[]{"Pennsylvania"}, new int[]{812, 877, 945, 946, 878, 809 }, new int[]{ 524, 541, 541, 551, 551, 534}));
        
        routesList.add(new Route(cities[23],cities[4],TrainColor.PINK , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{ 822, 855, 888, 922, 951, 955, 927, 894, 858, 822, }, new int[]{612, 610, 602, 592, 578, 587, 601, 613, 621, 624 }));
        routesList.add(new Route(cities[23],cities[4],TrainColor.YELLOW , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{819, 852, 886, 918, 949, 953,922, 853, 819 }, new int[]{ 601, 598, 590, 579, 565,574, 601, 608, 612}));
        
        routesList.add(new Route(cities[23],cities[3],TrainColor.WILD, 2 , new String[]{"Pennsylvania","Reading Railroad","Jersey Central Line"}, new int[]{ 992, 1058, 1057, 990}, new int[]{544, 553, 562, 555 }));
        routesList.add(new Route(cities[23],cities[3],TrainColor.WILD , 2 , new String[]{"Pennsylvania","Reading Railroad","Jersey Central Line"}, new int[]{992, 1058, 1056, 989 }, new int[]{555, 562, 576, 568 }));
        
        //Pittsburgh
        routesList.add(new Route(cities[24],cities[20],TrainColor.RED , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{290, 314, 324, 300 }, new int[]{ 439, 306, 308, 441}));
        routesList.add(new Route(cities[24],cities[35],TrainColor.BLUE , 4 , new String[]{"Pennsylvania","Baltimore","New York Central System"}, new int[]{195, 206, 240, 284, 275, 251, 230 }, new int[]{338, 332, 391, 439, 447, 425, 398,  }));
        routesList.add(new Route(cities[24],cities[35],TrainColor.ORANGE , 4 , new String[]{"Pennsylvania","Baltimore","New York Central System"}, new int[]{ 187, 195, 228, 274, 266, 219, 201}, new int[]{ 343, 338, 398, 448, 457, 404, 374}));
        routesList.add(new Route(cities[24],cities[32],TrainColor.GREEN  , 2 , new String[]{"Pennsylvania"}, new int[]{ 208, 257, 266, 213}, new int[]{ 506, 463, 473, 514}));
        routesList.add(new Route(cities[24],cities[32],TrainColor.WHITE , 2 , new String[]{"Pennsylvania"}, new int[]{213, 266, 272, 221 }, new int[]{ 213, 266, 272, 221}));
        routesList.add(new Route(cities[24],cities[18],TrainColor.YELLOW , 3 , new String[]{}, new int[]{277, 286, 296,285  }, new int[]{490, 490, 590, 591 }));
        routesList.add(new Route(cities[24],cities[15],TrainColor.PINK , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{301, 362, 433, 434, 365, 296 }, new int[]{452, 465, 465, 476, 476, 462 }));
        routesList.add(new Route(cities[24],cities[15],TrainColor.BLACK , 4 , new String[]{"Pennsylvania","Baltimore"}, new int[]{299, 365, 434, 432, 365, 295 }, new int[]{452, 476, 475, 488, 491, 474 }));
        
        //Reading 
        routesList.add(new Route(cities[25],cities[1],TrainColor.GREEN , 2 , new String[]{"Reading Railroad"}, new int[]{825, 887, 892, 830 }, new int[]{ 454, 432, 442, 464 }));
        routesList.add(new Route(cities[25],cities[14],TrainColor.PINK , 2 , new String[]{"Reading Railroad"}, new int[]{735, 801, 801, 734 }, new int[]{ 469, 462, 473, 478}));
        routesList.add(new Route(cities[25],cities[16],TrainColor.YELLOW ,1  , new String[]{"Reading Railroad"}, new int[]{799, 807, 818, 810 }, new int[]{513, 482, 484, 516 }));
        //Rochester
        routesList.add(new Route(cities[26],cities[29],TrainColor.PINK ,4  , new String[]{"Lehigh Valley","New York Central System"}, new int[]{ }, new int[]{ }));
        routesList.add(new Route(cities[26],cities[29],TrainColor.BLUE ,4  , new String[]{"Lehigh Valley","New York Central System"}, new int[]{ }, new int[]{ }));
        routesList.add(new Route(cities[26],cities[6],TrainColor.BLACK , 5 , new String[]{"Baltimore","Erie Lackawanna","Lehigh Valley","New York Central System","BRP RailWay"}, new int[]{ 484, 653, 653, 484}, new int[]{56,56,66,66 }));
        routesList.add(new Route(cities[26],cities[6],TrainColor.YELLOW ,5  , new String[]{"Baltimore","Erie Lackawanna","Lehigh Valley","New York Central System","BRP RailWay"}, new int[]{484 ,653,653,484 }, new int[]{ 43,43,53,53}));
        routesList.add(new Route(cities[26],cities[11],TrainColor.GREEN , 3 , new String[]{"Pennsylvania","Baltimore","Erie Lackawanna","Lehigh Valley","New York Central System","BRP RailWay"}, new int[]{663, 673, 667, 680, 708, 702, 674, 658 }, new int[]{64, 67, 98, 129, 148, 158,141, 104 }));
        //Scranton
        routesList.add(new Route(cities[27],cities[5],TrainColor.GREEN ,3  , new String[]{"Erie Lackawanna"}, new int[]{869, 880, 898, 887 }, new int[]{ 177, 176, 274, 276}));
        routesList.add(new Route(cities[27],cities[5],TrainColor.BLACK , 3 , new String[]{"Erie Lackawanna"}, new int[]{ 856, 866, 884, 875}, new int[]{179, 178, 276, 278 }));
        routesList.add(new Route(cities[27],cities[30],TrainColor.WILD , 3 , new String[]{"Pennsylvania","Erie Lackawanna","Lehigh Valley"}, new int[]{789, 875, 868, 782 }, new int[]{232, 285, 293, 241 }));
        routesList.add(new Route(cities[27],cities[33],TrainColor.ORANGE ,5  , new String[]{}, new int[]{705, 872, 873, 704 }, new int[]{314, 297, 310, 326 }));
        routesList.add(new Route(cities[27],cities[14],TrainColor.WILD , 6 , new String[]{"Pennsylvania","Reading Railroad"}, new int[]{ 722, 861, 869, 731}, new int[]{462, 315, 320, 470 }));
        routesList.add(new Route(cities[27],cities[1],TrainColor.WHITE ,3  , new String[]{"Pennsylvania","Reading Railroad","Lehigh Valley","Jersey Central Line"}, new int[]{ 873, 872, 876, 884,898,888,884,886   }, new int[]{314, 347, 382, 417, 414, 380,344,313 }));
        routesList.add(new Route(cities[27],cities[1],TrainColor.BLUE , 3 , new String[]{"Pennsylvania","Reading Railroad","Lehigh Valley","Jersey Central Line"}, new int[]{886, 884, 888, 898, 908,899, 896,898  }, new int[]{313, 344, 380, 414, 413, 381, 348, 315 }));
        routesList.add(new Route(cities[27],cities[28],TrainColor.YELLOW ,2  , new String[]{"Reading Railroad","Lehigh Valley","Jersey Central Line"}, new int[]{911, 874, 869, 782 }, new int[]{311, 284, 294, 243 }));
        routesList.add(new Route(cities[27],cities[19],TrainColor.RED , 5 , new String[]{"Erie Lackawanna","Lehigh Valley","Jersey Central Line"}, new int[]{900, 967, 1003, 1073, 1068, 1004, 969, 900  }, new int[]{ 282, 284, 291, 312, 324,303, 297, 293 }));
        routesList.add(new Route(cities[27],cities[19],TrainColor.PINK ,5  , new String[]{"Erie Lackawanna","Lehigh Valley","Jersey Central Line"}, new int[]{900, 969, 1004, 1068, 1066, 999, 899  }, new int[]{293, 297, 303, 324, 335, 317, 308 }));
        //Stroudsburg
        routesList.add(new Route(cities[28],cities[27],TrainColor.YELLOW , 2 , new String[]{"Reading Railroad","Lehigh Valley","Jersey Central Line"}, new int[]{911, 874, 869, 782 }, new int[]{311, 284, 294, 243 }));
        routesList.add(new Route(cities[28],cities[1],TrainColor.ORANGE , 2 , new String[]{"Reading Railroad","Lehigh Valley","Jersey Central Line"}, new int[]{913, 961, 969, 921 }, new int[]{ 419, 372, 380,427}));
        //Syracuse
        routesList.add(new Route(cities[28],cities[26],TrainColor.PINK , 4 , new String[]{"Lehigh Valley","New York Central System"}, new int[]{ 688, 823, 824, 688}, new int[]{ 43, 44, 53, 54}));
        routesList.add(new Route(cities[28],cities[26],TrainColor.BLUE , 4 , new String[]{"Lehigh Valley","New York Central System"}, new int[]{688, 823, 824, 688 }, new int[]{ 56, 56, 65, 65}));
        routesList.add(new Route(cities[28],cities[11],TrainColor.BLACK , 4 , new String[]{"Erie Lackawanna","Lehigh Valley"}, new int[]{720, 827, 834, 160 }, new int[]{152, 69, 77, 160 }));
        routesList.add(new Route(cities[28],cities[5],TrainColor.YELLOW ,2  , new String[]{"Erie Lackawanna"}, new int[]{ 841, 850, 861, 850}, new int[]{81, 79, 145, 146 }));
        routesList.add(new Route(cities[28],cities[5],TrainColor.ORANGE ,2  , new String[]{"Erie Lackawanna"}, new int[]{ 850, 861, 872, 863}, new int[]{79, 77, 142, 144 }));
        routesList.add(new Route(cities[28],cities[0],TrainColor.RED , 6 , new String[]{"New York Central System"}, new int[]{858, 1061, 1060, 857 }, new int[]{ 53,73,85,65}));
        routesList.add(new Route(cities[28],cities[0],TrainColor.WHITE , 6 , new String[]{"New York Central System"}, new int[]{859,1061, 1061, 858 }, new int[]{ 42,62,73,53}));
        
        //Towanda
        routesList.add(new Route(cities[28],cities[5],TrainColor.RED , 2 , new String[]{"Erie Lackawanna"}, new int[]{787, 839, 847,791  }, new int[]{212, 174, 183, 221 }));
        routesList.add(new Route(cities[28],cities[11],TrainColor.YELLOW , 2 , new String[]{"Pennsylvania","Erie Lackawanna","Lehigh Valley"}, new int[]{ 713, 723, 732, 761, 757,727,721}, new int[]{179, 176, 207,223, 231,218, 208 }));
        routesList.add(new Route(cities[28],cities[33],TrainColor.BLACK , 2 , new String[]{"Erie Lackawanna","Reading Railroad"}, new int[]{707, 752, 759, 714 }, new int[]{295, 245, 253, 301 }));
        routesList.add(new Route(cities[28],cities[27],TrainColor.WILD , 3 , new String[]{"Pennsylvania","Erie Lackawanna","Lehigh Valley"}, new int[]{789, 875, 868, 782 }, new int[]{232, 285, 293, 241 }));
        //Warren
        routesList.add(new Route(cities[28],cities[6],TrainColor.GREEN , 4 , new String[]{"Pennsylvania","Erie Lackawanna","Baltimore","BRP RailWay"}, new int[]{ 465, 404, 414, 476}, new int[]{76,197,202,87 }));
        routesList.add(new Route(cities[28],cities[12],TrainColor.BLUE , 3 , new String[]{"Pennsylvania","Erie Lackawanna","New York Central System"}, new int[]{292, 296, 389, 387 }, new int[]{174, 163, 200, 211 }));
        routesList.add(new Route(cities[28],cities[20],TrainColor.ORANGE , 2 , new String[]{"Pennsylvania","Erie Lackawanna","Baltimore"}, new int[]{338, 384, 393, 346 }, new int[]{ 272, 224, 232, 280}));
        routesList.add(new Route(cities[28],cities[10],TrainColor.BLACK , 3 , new String[]{"Erie Lackawanna","BRP RailWay"}, new int[]{448,404,414,458 }, new int[]{329,236,232,324 }));
        routesList.add(new Route(cities[28],cities[8],TrainColor.WILD , 4 , new String[]{"Pennsylvania","Erie Lackawanna","Baltimore","New York Central System","BRP RailWay"}, new int[]{550,516,580,444,411,413,446,479,514,547 }, new int[]{226,232,234,232,225,213,220,222,220,214 }));
        //Wheeling
        routesList.add(new Route(cities[28],cities[24],TrainColor.GREEN , 2 , new String[]{"Pennsylvania"}, new int[]{208, 257, 266, 213 }, new int[]{ 506, 463, 473, 514}));
        routesList.add(new Route(cities[28],cities[24],TrainColor.WHITE ,2  , new String[]{"Pennsylvania"}, new int[]{214, 267, 273 , 221 }, new int[]{515, 474, 482, 523 }));
        routesList.add(new Route(cities[28],cities[35],TrainColor.PINK , 5 , new String[]{"Pennsylvania","Baltimore","New York Central System"}, new int[]{173, 171, 174, 180, 190, 199, 182, 185 }, new int[]{ 341, 409, 444, 482, 514, 512, 412, 342}));
        routesList.add(new Route(cities[28],cities[18],TrainColor.BLUE ,3  , new String[]{"Baltimore"}, new int[]{209, 225, 249, 279, 276, 246, 218, 199 }, new int[]{ 540, 570, 592, 605,615, 604, 579, 545}));
        //Williamsport
        routesList.add(new Route(cities[28],cities[27],TrainColor.ORANGE ,5  , new String[]{}, new int[]{705, 872, 873, 704}, new int[]{ 314, 297, 310, 326}));
        routesList.add(new Route(cities[28],cities[30],TrainColor.BLACK , 2 , new String[]{"Erie Lackawanna","Reading Railroad" }, new int[]{707, 752, 759, 714}, new int[]{295, 245, 253, 301}));
        routesList.add(new Route(cities[28],cities[8],TrainColor.GREEN , 4 , new String[]{"Pennsylvania"}, new int[]{575, 568, 578, 585 }, new int[]{ 225, 234, 314, 305}));
        routesList.add(new Route(cities[28],cities[10],TrainColor.WHITE , 6 , new String[]{}, new int[]{473, 474, 679, 677 }, new int[]{340, 350, 329, 318 }));
        routesList.add(new Route(cities[28],cities[17],TrainColor.YELLOW , 3 , new String[]{"Pennsylvania", "Reading Railroad"}, new int[]{625, 682, 692, 632 }, new int[]{ 415, 336, 340, 422}));
        //York
        routesList.add(new Route(cities[28],cities[16],TrainColor.PINK , 1 , new String[]{"Pennslyvania"}, new int[]{ 753, 785, 786, 755}, new int[]{ 540, 535, 546, 550}));
        routesList.add(new Route(cities[28],cities[14],TrainColor.BLACK ,1  , new String[]{"BRP RailWay","Pennsylvania"}, new int[]{711, 720, 734, 724 }, new int[]{509, 505, 534,538 }));
        routesList.add(new Route(cities[28],cities[13],TrainColor.WILD , 1 , new String[]{"BRP RailWay","Pennsylvania"}, new int[]{749, 798, 791, 743 }, new int[]{551, 597, 605, 560 }));
        routesList.add(new Route(cities[28],cities[4],TrainColor.WHITE , 2 , new String[]{"BRP RailWay","Pennsylvania"}, new int[]{691, 721, 724, 695 }, new int[]{558, 546,556,567 }));
        //Youngstown
        routesList.add(new Route(cities[28],cities[20],TrainColor.WHITE , 3 , new String[]{"Erie Lackawanna"}, new int[]{206, 303, 305, 207 }, new int[]{318, 292, 301, 328 }));
        routesList.add(new Route(cities[28],cities[12],TrainColor.GREEN , 4 , new String[]{"Erie Lackawanna","New York Central System" }, new int[]{264, 274, 213, 203}, new int[]{186, 191, 311,307}));
        routesList.add(new Route(cities[28],cities[12],TrainColor.YELLOW , 5 , new String[]{"Erie Lackawanna","New York Central System"}, new int[]{ 254, 264, 203, 192}, new int[]{181, 186, 307, 301 }));
        routesList.add(new Route(cities[28],cities[32],TrainColor.PINK, 4 , new String[]{"New York Central System","Jersey Central Line", "Pennsylvania" }, new int[]{}, new int[]{}));
        routesList.add(new Route(cities[28],cities[24],TrainColor.ORANGE, 4 , new String[]{"New York Central System","Jersey Central Line", "Pennsylvania"}, new int[]{187, 195, 228, 274, 266, 219, 201 }, new int[]{343, 338, 398, 448, 457, 404, 374 }));
        routesList.add(new Route(cities[28],cities[24],TrainColor.BLUE, 4 , new String[]{"New York Central System","Jersey Central Line", "Pennsylvania"}, new int[]{195, 206, 240, 284, 275, 251, 230}, new int[]{338, 332, 391, 439, 447, 425, 398}));
        
        routes = (Route[])routesList.toArray(new Route[]{});
        
       
        //Create List of Destination Cards    
        dest = new ArrayList<>();
        
        dest.add(new DestCard(cities[30],cities[16], 9, "routes1.jpg"));
        dest.add(new DestCard(cities[18],cities[33], 13 ,"routes2.jpg"));
        dest.add(new DestCard(cities[32],cities[12], 9 ,"routes3.jpg"));
        dest.add(new DestCard(cities[32],cities[1], 15 ,"routes4.jpg"));
        dest.add(new DestCard(cities[14],cities[19], 11 ,"routes5.jpg"));
        dest.add(new DestCard(cities[2],cities[5], 9 ,"routes6.jpg"));
        dest.add(new DestCard(cities[17],cities[29], 9 ,"routes7.jpg"));
        dest.add(new DestCard(cities[4],cities[0],16 ,"routes8.jpg"));
        dest.add(new DestCard(cities[14],cities[23],6  ,"routes9.jpg"));
        dest.add(new DestCard(cities[27],cities[1], 3 ,"routes10.jpg"));
        dest.add(new DestCard(cities[26],cities[11], 3 ,"routes11.jpg"));
        dest.add(new DestCard(cities[12],cities[0], 20 ,"routes12.jpg"));
        dest.add(new DestCard(cities[29],cities[1], 8 ,"routes13.jpg"));
        dest.add(new DestCard(cities[12],cities[2],8  ,"routes14.jpg"));
        dest.add(new DestCard(cities[18],cities[7], 7 ,"routes15.jpg"));
        //DOUBLE CHECK ON DESTINATION 16 ASK LONDON HOW HIS LOGIC TACKLE THIS
        dest.add(new DestCard(cities[21],cities[29], 10 ,"routes16.jpg"));
        dest.add(new DestCard(cities[22],cities[29],10  ,"routes16.jpg"));
        //ANOTHER ONTARIO CARD
        dest.add(new DestCard(cities[21],cities[12], 5 ,"routes17.jpg"));
        dest.add(new DestCard(cities[22],cities[12], 5 ,"routes17.jpg"));
        
        dest.add(new DestCard(cities[3],cities[23], 2 ,"routes18.jpg"));
        dest.add(new DestCard(cities[10],cities[9], 6 ,"routes19.jpg"));
        dest.add(new DestCard(cities[10],cities[28],12  ,"routes20.jpg"));
        dest.add(new DestCard(cities[6],cities[14], 13 ,"routes21.jpg"));
        dest.add(new DestCard(cities[6],cities[15],10  ,"routes22.jpg"));
        dest.add(new DestCard(cities[6],cities[19], 18 ,"routes23.jpg"));
        dest.add(new DestCard(cities[6],cities[23],19  ,"routes24.jpg"));
        //ANOTHER ONTARIO CARD
        dest.add(new DestCard(cities[21],cities[24], 10 ,"routes25.jpg"));
        dest.add(new DestCard(cities[23],cities[24],10  ,"routes25.jpg"));
        
        dest.add(new DestCard(cities[9],cities[14],4  ,"routes26.jpg"));
        dest.add(new DestCard(cities[33],cities[0], 10 ,"routes27.jpg"));
        dest.add(new DestCard(cities[4],cities[19], 10 ,"routes28.jpg"));
        dest.add(new DestCard(cities[4],cities[23], 4 ,"routes29.jpg"));
        dest.add(new DestCard(cities[6],cities[4],16  ,"routes30.jpg"));
        dest.add(new DestCard(cities[24],cities[23], 15 ,"routes31.jpg"));
        dest.add(new DestCard(cities[32],cities[0],22  ,"routes32.jpg"));
        dest.add(new DestCard(cities[35],cities[27], 17 ,"routes33.jpg"));
        dest.add(new DestCard(cities[7],cities[27], 8 ,"routes34.jpg"));
        dest.add(new DestCard(cities[8],cities[5],7  ,"routes35.jpg"));
        dest.add(new DestCard(cities[31],cities[34],10  ,"routes36.jpg"));
        dest.add(new DestCard(cities[13],cities[25], 3 ,"routes37.jpg"));
        dest.add(new DestCard(cities[35],cities[18], 7 ,"routes38.jpg"));
        dest.add(new DestCard(cities[15],cities[11], 10 ,"routes39.jpg"));
        dest.add(new DestCard(cities[26],cities[25],13  ,"routes40.jpg"));
        dest.add(new DestCard(cities[24],cities[6], 10 ,"routes41.jpg"));
        dest.add(new DestCard(cities[14],cities[4], 3 ,"routes42.jpg"));
        dest.add(new DestCard(cities[35],cities[26], 14 ,"routes43.jpg"));
        dest.add(new DestCard(cities[24],cities[14], 9 ,"routes44.jpg"));
        dest.add(new DestCard(cities[24],cities[19],20  ,"routes45.jpg"));
        dest.add(new DestCard(cities[19],cities[3],6  ,"routes46.jpg"));
        dest.add(new DestCard(cities[20],cities[15],6  ,"routes47.jpg"));
        dest.add(new DestCard(cities[20],cities[27],14  ,"routes48.jpg"));
        dest.add(new DestCard(cities[23],cities[19],6  ,"routes49.jpg"));
        dest.add(new DestCard(cities[24],cities[4],13  ,"routes50.jpg"));
        
        
    }

}

