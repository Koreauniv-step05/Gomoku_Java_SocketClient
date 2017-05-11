package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.JYP.EverySequences.SequenceSymbol.*;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_BOARD.*;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.consts.GAME_EVAL.GAMEEND;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class EverySequences {
    interface SequenceSymbol {
        double ZERO = 0f;
        double FIVE_SEQ = 10000.0f;
        double FOUR_SEQ = 1000.0f;
        double THREE_SEQ = 100.0f;
        double TWO_SEQ = 10.0f;
        double ONE_SEQ = 1.0f;
        double ZERO_SEQ = 0.1f;

        String A = ""+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE;
        double A_SCORE = GAMEEND*2;
        String AA = ""+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE;
        double AA_SCORE = FIVE_SEQ*2;
        //  011110 INF Score1000
        String AB = ""+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE;
        double AB_SCORE = FIVE_SEQ*2;

        // -111110 4R Score1000
        String BA = ""+WHITE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE;
        String BAR = ""+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+WHITE_STONE;
        double BA_SCORE = FOUR_SEQ;

        //  0011100 3 Score100
        String CA = ""+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE;
        double CA_SCORE = THREE_SEQ;
        // -1011100 3R
        String CB = ""+WHITE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE;
        String CBR = ""+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+WHITE_STONE;
        double CB_SCORE = THREE_SEQ-TWO_SEQ;
        // -111100 3R
        String CC = ""+WHITE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE;
        String CCR = ""+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+BLACK_STONE+WHITE_STONE;
        double CC_SCORE = THREE_SEQ-TWO_SEQ*2;


        // 00011000 2 Score10
        String DA = ""+NONE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        double DA_SCORE = TWO_SEQ;
        // -10011000 2R
        String DB = ""+WHITE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String DBR = ""+NONE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double DB_SCORE = TWO_SEQ-ONE_SEQ;

        // -1011000 2R
        String DC = ""+WHITE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String DCR = ""+NONE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+WHITE_STONE;
        double DC_SCORE = TWO_SEQ-ONE_SEQ*2;
        // -111000 2R
        String DD = ""+WHITE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String DDR = ""+NONE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+WHITE_STONE;
        double DD_SCORE = TWO_SEQ-ONE_SEQ*3;

        // -1001100-1 2
        String DE = ""+WHITE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double DE_SCORE = TWO_SEQ-ONE_SEQ*4;
        // -100110-1 2R
        String DF = ""+WHITE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+WHITE_STONE;
        String DFR = ""+WHITE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double DF_SCORE = TWO_SEQ-ONE_SEQ*5;
        // -101100-1 2R
        String DG = ""+WHITE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        String DGR = ""+WHITE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+BLACK_STONE+NONE_STONE+WHITE_STONE;
        double DG_SCORE = TWO_SEQ-ONE_SEQ*6;

        // 000010000 Score1
        String EA = ""+NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        double EA_SCORE = ONE_SEQ;
        // -100010000
        String EB = ""+WHITE_STONE +NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String EBR = ""+NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double EB_SCORE = ONE_SEQ-ZERO_SEQ;

        // -10010000
        String EC = ""+WHITE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String ECR = ""+NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+WHITE_STONE;
        double EC_SCORE = ONE_SEQ-ZERO_SEQ*2;
        // -1010000
        String ED = ""+WHITE_STONE +NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String EDR = ""+NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+WHITE_STONE;
        double ED_SCORE = ONE_SEQ-ZERO_SEQ*3;
        // -110000
        String EE = ""+WHITE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE;
        String EER = "" +NONE_STONE+NONE_STONE+NONE_STONE+NONE_STONE+BLACK_STONE+WHITE_STONE;
        double EE_SCORE = ONE_SEQ-ZERO_SEQ*4;

        // -10001000-1
        String EF = ""+WHITE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double EF_SCORE = ONE_SEQ-ZERO_SEQ*5;
        // -1000100-1
        String EG = ""+WHITE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+WHITE_STONE;
        String EGR = ""+WHITE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double EG_SCORE = ONE_SEQ-ZERO_SEQ*5;
        // -100010-1
        String EH = ""+WHITE_STONE +NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+WHITE_STONE;
        String EHR = ""+WHITE_STONE +NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE+WHITE_STONE;
        double EH_SCORE = ONE_SEQ-ZERO_SEQ*5;

        // -1001000-1
        String EI = ""+WHITE_STONE +NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE +WHITE_STONE;
        String EIR = ""+WHITE_STONE +NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+WHITE_STONE;
        double EI_SCORE = ONE_SEQ-ZERO_SEQ*5;
        // -100100-1
        String EJ = ""+WHITE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +NONE_STONE+NONE_STONE+ WHITE_STONE;
        double EJ_SCORE = ONE_SEQ-ZERO_SEQ*5;

        // -101000-1
        String EK = ""+WHITE_STONE+ NONE_STONE+ BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE +WHITE_STONE;
        String EKR = ""+WHITE_STONE+ NONE_STONE+NONE_STONE +NONE_STONE+ BLACK_STONE +NONE_STONE+WHITE_STONE;
        double EK_SCORE = ONE_SEQ-ZERO_SEQ*5;

        // -10001-1
        String EL = ""+WHITE_STONE+NONE_STONE+NONE_STONE+NONE_STONE +BLACK_STONE +WHITE_STONE;
        String ELR = ""+WHITE_STONE+BLACK_STONE +NONE_STONE+NONE_STONE+NONE_STONE +WHITE_STONE;
        double EL_SCORE = ONE_SEQ-ZERO_SEQ*5;
    }

    public static List<Sequence> getEverySequences() {
        if(everySequences == null) {
            log.info("EverySequences/getEverySequences: initialize");
            everySequences = new ArrayList<Sequence>();
            everySequences.add(new Sequence(A,A_SCORE));
            everySequences.add(new Sequence(AA,AA_SCORE));
            everySequences.add(new Sequence(AB,AB_SCORE));
            everySequences.add(new Sequence(BA,BA_SCORE));
            everySequences.add(new Sequence(BAR,BA_SCORE));
            everySequences.add(new Sequence(CA,CA_SCORE));
            everySequences.add(new Sequence(CB,CB_SCORE));
            everySequences.add(new Sequence(CBR,CB_SCORE));
            everySequences.add(new Sequence(CC,CC_SCORE));
            everySequences.add(new Sequence(CCR,CC_SCORE));
            everySequences.add(new Sequence(DA,DA_SCORE));
            everySequences.add(new Sequence(DB,DB_SCORE));
            everySequences.add(new Sequence(DBR,DB_SCORE));
            everySequences.add(new Sequence(DC,DC_SCORE));
            everySequences.add(new Sequence(DCR,DC_SCORE));
            everySequences.add(new Sequence(DD,DD_SCORE));
            everySequences.add(new Sequence(DDR,DD_SCORE));
            everySequences.add(new Sequence(DE,DE_SCORE));
            everySequences.add(new Sequence(DF,DF_SCORE));
            everySequences.add(new Sequence(DFR,DF_SCORE));
            everySequences.add(new Sequence(DF,DF_SCORE));
            everySequences.add(new Sequence(DG,DG_SCORE));
            everySequences.add(new Sequence(DGR,DG_SCORE));
            everySequences.add(new Sequence(EA,EA_SCORE));
            everySequences.add(new Sequence(EB,EB_SCORE));
            everySequences.add(new Sequence(EBR,EB_SCORE));
            everySequences.add(new Sequence(EC,EC_SCORE));
            everySequences.add(new Sequence(ECR,EC_SCORE));
            everySequences.add(new Sequence(ED,ED_SCORE));
            everySequences.add(new Sequence(EDR,ED_SCORE));
            everySequences.add(new Sequence(EE,EE_SCORE));
            everySequences.add(new Sequence(EER,EE_SCORE));
            everySequences.add(new Sequence(EF,EF_SCORE));
            everySequences.add(new Sequence(EG,EG_SCORE));
            everySequences.add(new Sequence(EGR,EG_SCORE));
            everySequences.add(new Sequence(EH,EH_SCORE));
            everySequences.add(new Sequence(EHR,EH_SCORE));
            everySequences.add(new Sequence(EI,EI_SCORE));
            everySequences.add(new Sequence(EIR,EI_SCORE));
            everySequences.add(new Sequence(EJ,EJ_SCORE));
            everySequences.add(new Sequence(EK,EK_SCORE));
            everySequences.add(new Sequence(EKR,EK_SCORE));
            everySequences.add(new Sequence(EK,EK_SCORE));
            everySequences.add(new Sequence(EL,EL_SCORE));
            everySequences.add(new Sequence(ELR,EL_SCORE));
        }
        
        return everySequences;
    }

    private static List<Sequence> everySequences;
}
