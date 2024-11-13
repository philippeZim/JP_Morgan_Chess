package Model

import Model.LegalMoves
import Model.LegalMoves.*
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class LegalMovesSpec extends AnyWordSpec {
    "Legal Moves " should {
        "return all legal moves for fens" in {
            val expected: List[(Int, Int)] = List((24, 9), (24, 18), (24, 34), (55, 7), (55, 15), (55, 23), (55, 31), (55, 39), (55, 47), (55, 54), (55, 53), (55, 52), (55, 51), (55, 63), (50, 43), (50, 51), (50, 59), (61, 45), (61, 53), (61, 63), (61, 62), (61, 60), (61, 59), (58, 44), (58, 51), (58, 49), (37, 28), (30, 22), (41, 33), (40, 32)).sorted;
            val fen = "rn1kr3/8/p1p5/Np1bbpP1/5P2/PP6/b1K4R/2B2R2 w - - 5 65";
            LegalMoves.getAllLegalMoves(fen).sorted should be(expected);

            val expected0: List[(Int, Int)] = List((5, 14), (5, 13), (5, 12), (10, 2), (10, 15), (10, 14), (10, 13), (10, 12), (10, 11), (10, 9), (10, 18), (10, 26), (10, 34), (10, 42), (10, 50), (10, 58), (62, 45), (62, 52), (47, 55)).sorted;
            val fen0 = "2B2k2/1Rr5/5N2/p7/p3P3/P5Kp/8/4R1n1 b - - 6 76";
            LegalMoves.getAllLegalMoves(fen0).sorted should be(expected0)

            val expected1: List[(Int, Int)] = List((19, 11), (19, 27), (50, 15), (50, 22), (50, 29), (50, 36), (50, 32), (50, 43), (50, 41), (50, 59), (50, 57), (24, 32)).sorted;
            val fen1 = "8/8/1K1k4/p7/8/4R3/2b5/R7 b - - 7 88";
            LegalMoves.getAllLegalMoves(fen1).sorted should be(expected1)

            val expected2: List[(Int, Int)] = List((7, 15), (6, 21), (3, 4), (3, 2), (1, 18), (1, 16), (0, 8), (0, 16), (0, 24), (0, 32), (11, 4), (11, 2), (11, 19), (11, 18), (20, 13), (20, 29), (20, 27), (20, 34), (20, 41), (20, 48), (40, 49), (14, 22), (10, 18), (9, 17), (26, 34), (14, 30), (9, 25)).sorted;
            val fen2 = "rn1q1bnr/1ppkp1p1/4b2p/2p2P1P/5P2/p2P4/PP3NP1/RNBQKB1R b KQ - 0 10";
            LegalMoves.getAllLegalMoves(fen2).sorted should be(expected2)


            val expected3: List[(Int, Int)] = List((45, 30), (45, 28), (45, 39), (45, 35), (45, 55), (45, 51), (45, 62), (45, 60), (54, 47), (54, 61), (50, 32), (50, 41), (50, 51), (50, 59), (50, 58), (63, 39), (63, 47), (63, 55), (63, 62), (63, 61), (63, 60), (63, 59), (63, 58), (57, 40), (57, 51), (12, 5), (12, 5), (12, 5), (12, 5), (46, 38), (43, 35), (42, 34), (49, 41), (48, 40), (49, 33), (48, 32)).sorted.distinct;
            val fen3 = "1rb1kb1r/ppp1P2p/7n/n4ppP/8/2PP1NP1/PPQ1KPB1/RNq4R w - - 0 19";
            LegalMoves.getAllLegalMoves(fen3).sorted should be(expected3)



            val expected4: List[(Int, Int)] = List((2, 1), (2, 11)).sorted;
            val fen4 = "2k5/3R4/2R3K1/6b1/6b1/8/3P1r2/6NB b - - 6 116";
            LegalMoves.getAllLegalMoves(fen4).sorted should be(expected4)

            val expected5: List[(Int, Int)] = List((27, 19), (42, 47), (42, 46), (42, 45), (42, 44), (42, 43), (42, 41), (42, 50), (42, 58), (40, 25), (40, 50), (40, 57), (62, 47), (62, 45), (62, 52), (28, 20)).sorted;
            val fen5 = "r4n2/b2p1p2/n7/P1pKPk2/2P5/N1R5/P7/5bN1 w - - 0 66";
            LegalMoves.getAllLegalMoves(fen5).sorted should be(expected5)

            val expected6: List[(Int, Int)] = List((56, 48), (56, 57), (19, 35), (51, 42)).sorted;
            val fen6 = "6k1/8/P2R4/4b3/6P1/8/3B4/K7 w - - 3 101";
            LegalMoves.getAllLegalMoves(fen6).sorted should be(expected6)

            val expected7: List[(Int, Int)] = List((30, 22), (30, 31), (30, 29), (30, 28), (30, 27), (30, 26), (30, 25), (30, 24), (30, 38), (30, 46), (30, 54), (30, 62), (44, 37), (44, 35), (44, 53), (44, 51), (44, 62), (44, 58), (61, 54), (61, 53), (61, 62), (61, 60), (59, 35), (59, 43), (59, 51), (59, 60), (59, 58), (59, 57), (59, 56), (19, 10), (42, 35), (23, 15), (40, 32)).sorted;
            val fen7 = "4k3/2bb1r2/3PpPnP/6R1/2rn4/P1P1B3/8/3R1K2 w - - 7 62";
            LegalMoves.getAllLegalMoves(fen7).sorted should be(expected7)

            val expected8: List[(Int, Int)] = List((18, 10), (18, 23), (18, 22), (18, 21), (18, 20), (18, 19), (18, 17), (18, 26), (18, 34), (31, 14), (31, 21), (31, 37), (31, 46), (53, 62), (53, 60), (51, 24), (51, 33), (51, 42), (51, 60), (51, 58), (63, 62), (54, 47), (16, 8), (44, 36), (40, 32), (54, 46)).sorted;
            val fen8 = "2bk1b2/2p5/P1R5/1p2p2N/2p3rp/P3P2q/3BPKPP/5B1R w - - 3 35";
            LegalMoves.getAllLegalMoves(fen8).sorted should be(expected8)

            val expected9: List[(Int, Int)] = List((2, 12), (2, 8), (2, 19), (2, 17), (43, 15), (43, 22), (43, 16), (43, 29), (43, 27), (43, 25), (43, 36), (43, 35), (43, 34), (43, 47), (43, 46), (43, 45), (43, 44), (43, 42), (43, 41), (43, 40), (43, 50), (55, 15), (55, 23), (55, 31), (55, 39), (55, 47), (55, 63), (52, 44), (52, 53), (52, 61), (52, 60), (49, 7), (49, 14), (49, 21), (49, 28), (49, 35), (49, 42), (49, 40), (49, 58), (59, 32), (59, 41), (59, 50), (57, 42), (57, 40), (20, 11), (20, 12), (54, 46), (48, 40), (54, 38), (48, 32)).sorted;
            val fen9 = "2N1k1n1/1r1n3r/1p2P3/3b2p1/1b6/3Q4/PB1PK1PR/RN1B4 w - - 5 36";
            LegalMoves.getAllLegalMoves(fen9).sorted should be(expected9)

            val expected10: List[(Int, Int)] = List((7, 13), (7, 22), (3, 4), (3, 2), (3, 12), (3, 11), (3, 10), (9, 2), (9, 0), (9, 18), (9, 27), (19, 5), (19, 1), (19, 12), (19, 11), (19, 10), (19, 18), (19, 17), (19, 28), (19, 27), (19, 26), (19, 37), (19, 35), (19, 33), (19, 46), (19, 43), (19, 55), (19, 51), (19, 59), (21, 29), (20, 28), (16, 24)).sorted;
            val fen10 = "3k3n/1b6/p2qpp1p/7P/1R2p2R/4N2K/8/8 b - - 2 59";
            LegalMoves.getAllLegalMoves(fen10).sorted should be(expected10)

            val expected11: List[(Int, Int)] = List((25, 10), (25, 8), (25, 19), (25, 35), (25, 42), (54, 36), (54, 47), (54, 45), (62, 47), (62, 45), (61, 60), (59, 60), (58, 49), (56, 48), (56, 57), (46, 39), (40, 33), (46, 38), (40, 32), (55, 47), (53, 45), (52, 44), (51, 43), (50, 42), (53, 37), (51, 35), (50, 34)).sorted;
            val fen11 = "rnb1k1nr/pppp2pp/8/1N3p2/1b2p2q/P5P1/2PPPPBP/R1BQ1KNR w kq - 3 8";
            LegalMoves.getAllLegalMoves(fen11).sorted should be(expected11)

            val expected12: List[(Int, Int)] = List((4, 7), (4, 6), (4, 5), (4, 3), (4, 2), (4, 1), (4, 0), (4, 12), (4, 20), (4, 28), (4, 36), (4, 44), (4, 52), (4, 60), (18, 3), (18, 1), (18, 12), (18, 8), (18, 28), (18, 24), (18, 35), (18, 33), (45, 38), (45, 36), (45, 46), (45, 54), (45, 53), (45, 52)).sorted;
            val fen12 = "R3r3/8/2n2p2/5P2/8/5k2/3B4/1K1R4 b - - 6 130";
            LegalMoves.getAllLegalMoves(fen12).sorted should be(expected12)

            val expected13: List[(Int, Int)] = List((5, 14), (3, 4), (3, 2), (3, 1), (3, 0), (12, 4), (12, 19), (11, 4), (11, 2), (10, 4), (10, 0), (10, 27), (10, 25), (8, 0), (8, 9), (23, 7), (23, 15), (20, 27), (18, 27), (13, 21), (22, 30), (20, 28), (18, 26), (17, 25), (16, 24), (31, 39), (13, 29)).sorted;
            val fen13 = "3q1b2/r1nbkp2/ppp1p1pr/3P3p/PP2P3/3PK1PP/1B1NP1B1/R2Q2NR b - - 2 24";
            LegalMoves.getAllLegalMoves(fen13).sorted should be(expected13)


            val expected14: List[(Int, Int)] = List((1, 0), (1, 9), (1, 8), (37, 5), (37, 13), (37, 21), (37, 29), (37, 39), (37, 38), (37, 36), (37, 35), (37, 34), (37, 33), (37, 32), (37, 45), (18, 27), (31, 39), (55, 63), (55, 63), (55, 63), (55, 63)).sorted.distinct;
            val fen14 = "1k6/3P2B1/2p5/2KN2Pp/B4r2/5P2/P6p/5R2 b - - 3 57";
            LegalMoves.getAllLegalMoves(fen14).sorted should be(expected14)



            val expected15: List[(Int, Int)] = List((59, 60), (59, 58), (5, 45), (42, 52)).sorted;
            val fen15 = "2Q2R2/1p1nk3/3p4/P1r5/8/2N1Nb1n/1r6/1R1K4 w - - 3 71";
            LegalMoves.getAllLegalMoves(fen15).sorted should be(expected15)

            val expected16: List[(Int, Int)] = List((30, 15), (30, 13), (30, 20), (30, 36), (30, 47), (30, 45), (40, 25), (40, 34), (40, 57), (63, 62), (60, 53), (56, 57), (37, 28), (37, 29), (55, 47), (54, 46), (52, 44), (51, 43), (50, 42), (49, 41), (55, 39), (54, 38), (52, 36), (51, 35), (50, 34)).sorted;
            val fen16 = "rnbqk1nr/p1ppbppp/8/4p1N1/1p3P2/N7/PPPPP1PP/R1BQKB1R w KQkq - 1 5";
            LegalMoves.getAllLegalMoves(fen16).sorted should be(expected16)

            val expected17: List[(Int, Int)] = List((7, 13), (7, 22), (36, 29), (36, 28), (36, 37), (36, 45), (36, 44), (43, 16), (43, 25), (43, 34), (43, 52), (43, 50), (43, 61), (43, 57)).sorted;
            val fen17 = "7N/3r4/8/8/k3K3/3B4/8/8 w - - 58 143";
            LegalMoves.getAllLegalMoves(fen17).sorted should be(expected17)

            val expected18: List[(Int, Int)] = List((34, 6), (34, 13), (34, 20), (34, 27), (34, 25), (34, 43), (34, 41), (34, 52), (34, 48), (34, 61), (53, 46), (53, 45), (53, 44), (53, 54), (53, 52), (53, 62), (53, 61), (53, 60), (16, 8)).sorted;
            val fen18 = "8/8/P7/8/2B5/8/1k3K2/8 w - - 27 147";
            LegalMoves.getAllLegalMoves(fen18).sorted should be(expected18)

            val expected19: List[(Int, Int)] = List((5, 15), (5, 11), (5, 22), (5, 20), (49, 21), (49, 28), (49, 35), (49, 42), (49, 40), (61, 47), (61, 54), (59, 50), (59, 60), (58, 18), (58, 30), (58, 26), (58, 37), (58, 34), (58, 44), (58, 42), (58, 51), (58, 50), (57, 42), (57, 40), (57, 51), (29, 22), (25, 18), (33, 24), (43, 35), (52, 44), (48, 40), (52, 36), (48, 32)).sorted;
            val fen19 = "2r1kNnr/8/1ppp1pp1/pP3Pqp/1P6/3P3b/PB2P3/RNQK1B2 w - - 1 31";
            LegalMoves.getAllLegalMoves(fen19).sorted should be(expected19)

            val expected20: List[(Int, Int)] = List((20, 13), (20, 11), (20, 29), (20, 27), (20, 34), (35, 18), (35, 29), (35, 25), (35, 45), (35, 52), (35, 50), (41, 27), (41, 34), (41, 32), (41, 46), (41, 45), (41, 44), (41, 43), (41, 42), (41, 40), (41, 50), (41, 59), (49, 42), (49, 40), (49, 58), (49, 56), (48, 24), (48, 32), (48, 40), (48, 56), (63, 55), (63, 62), (63, 61), (60, 52), (60, 61), (60, 59), (36, 29), (33, 24), (36, 28), (51, 43)).sorted;
            val fen20 = "4k1n1/1r1n1p1r/b1p1B3/pp3p2/1P1NPP1p/1Q4pP/RB1P2P1/4K2R w - - 9 40";
            LegalMoves.getAllLegalMoves(fen20).sorted should be(expected20)

            val expected21: List[(Int, Int)] = List((5, 6), (5, 4), (5, 12), (14, 7), (14, 23), (14, 21), (14, 28), (13, 7), (13, 3), (13, 23), (13, 19), (13, 30), (13, 28), (35, 20), (35, 18), (35, 25), (35, 45), (35, 41), (35, 52), (35, 50), (60, 4), (60, 12), (60, 20), (60, 28), (60, 36), (60, 44), (60, 52), (60, 63), (60, 62), (60, 61), (60, 59), (60, 58), (60, 57), (16, 24)).sorted;
            val fen21 = "5k2/5nbR/p5P1/2P2p2/3n1K2/2P5/8/1N2r3 b - - 0 65";
            LegalMoves.getAllLegalMoves(fen21).sorted should be(expected21)

            val expected22: List[(Int, Int)] = List((19, 4), (19, 2), (19, 13), (19, 9), (19, 29), (19, 25), (19, 34), (38, 14), (38, 22), (38, 30), (38, 46), (38, 54), (51, 44), (51, 43), (51, 52), (51, 50), (51, 60), (62, 47), (62, 45), (62, 52), (61, 16), (61, 25), (61, 34), (61, 47), (61, 43), (61, 54), (61, 52), (59, 32), (59, 45), (59, 41), (59, 52), (59, 50), (59, 60), (56, 57), (36, 27), (39, 31), (37, 29), (36, 28), (42, 34), (53, 45), (49, 41), (48, 40), (49, 33)).sorted;
            val fen22 = "1nq2bnr/kppbr1pp/3N4/3p4/p3PPRP/2P5/PP1K1P2/R1BQ1BN1 w - - 5 24";
            LegalMoves.getAllLegalMoves(fen22).sorted should be(expected22)

            val expected23: List[(Int, Int)] = List((3, 12), (3, 10), (3, 21), (3, 17), (3, 30), (3, 24), (0, 9), (0, 18), (0, 27), (0, 36), (0, 45), (0, 54), (0, 63), (39, 31), (39, 30), (39, 38), (39, 47), (39, 46), (52, 37), (52, 35), (52, 46), (52, 42), (52, 62), (52, 58), (16, 8), (29, 21)).sorted;
            val fen23 = "B2B1k2/3P4/P7/5P2/7K/8/4N3/8 w - - 0 112";
            LegalMoves.getAllLegalMoves(fen23).sorted should be(expected23)

            val expected24: List[(Int, Int)] = List((27, 12), (27, 10), (27, 21), (27, 37), (27, 33), (27, 44), (27, 42), (25, 4), (25, 11), (25, 18), (25, 16), (25, 34), (25, 43), (25, 52), (32, 0), (32, 8), (32, 16), (32, 24), (32, 39), (32, 38), (32, 37), (32, 36), (32, 35), (32, 34), (32, 33), (54, 6), (54, 14), (54, 22), (54, 30), (54, 38), (54, 36), (54, 47), (54, 46), (54, 45), (54, 55), (54, 53), (54, 52), (54, 51), (54, 50), (54, 63), (54, 62), (61, 62), (61, 60), (17, 10)).sorted;
            val fen24 = "2b2k2/1pb5/1P6/1BrNp3/R6p/P7/2r3Q1/5K2 w - - 2 71";
            LegalMoves.getAllLegalMoves(fen24).sorted should be(expected24)

            val expected25: List[(Int, Int)] = List((14, 7), (14, 6), (14, 5), (14, 15), (14, 13), (14, 23), (14, 22), (14, 21), (14, 30), (47, 54), (47, 61), (45, 30), (45, 28), (45, 39), (45, 62), (45, 60), (52, 43), (52, 61), (52, 60), (52, 59), (51, 36), (51, 34), (51, 41), (51, 61), (51, 57), (63, 62), (63, 61), (63, 60), (63, 59), (56, 57), (44, 36), (42, 34), (49, 41), (48, 40), (49, 33)).sorted;
            val fen25 = "r3k3/p1q1ppQ1/1pp1bn1r/3p2p1/n2P2Pp/2P1PN1B/PP1NKP1P/R1B4R w q - 8 19";
            LegalMoves.getAllLegalMoves(fen25).sorted should be(expected25)

            val expected26: List[(Int, Int)] = List((9, 1), (9, 15), (9, 14), (9, 13), (9, 12), (9, 11), (9, 10), (9, 8), (9, 17), (9, 25), (9, 33), (9, 41), (9, 49), (9, 57), (29, 22), (29, 21), (29, 20), (29, 30), (29, 28), (29, 38), (29, 37), (29, 36)).sorted;
            val fen26 = "8/1r6/8/2K2k2/8/8/8/8 b - - 107 208";
            LegalMoves.getAllLegalMoves(fen26).sorted should be(expected26)

            val expected27: List[(Int, Int)] = List((6, 7), (6, 5), (6, 15), (6, 13), (2, 5), (2, 4), (2, 3), (2, 1), (2, 0), (2, 10), (2, 18), (12, 4), (12, 15), (12, 14), (12, 13), (12, 11), (12, 20), (12, 28), (12, 36), (12, 44), (12, 52), (12, 60), (9, 3), (9, 24), (8, 18), (8, 25), (26, 34)).sorted;
            val fen27 = "2r3k1/nn1Br3/R2p3P/1PpN4/8/5N2/2P4K/8 b - - 6 50";
            LegalMoves.getAllLegalMoves(fen27).sorted should be(expected27)

            val expected28: List[(Int, Int)] = List((6, 14), (5, 14), (5, 23), (3, 4), (3, 2), (3, 1), (3, 10), (0, 2), (0, 1), (13, 23), (13, 19), (13, 28), (11, 4), (11, 2), (11, 18), (11, 25), (11, 32), (20, 14), (20, 10), (20, 26), (20, 37), (20, 35), (29, 38), (43, 52), (15, 23), (8, 16), (17, 25), (29, 37), (27, 35), (43, 51), (15, 31), (8, 24)).sorted;
            val fen28 = "r2q1bkr/p2bpn1p/1p2n3/3p1pp1/6P1/PNPpPP2/RP2Q1BP/1NB2K1R b - - 0 20";
            LegalMoves.getAllLegalMoves(fen28).sorted should be(expected28)

            val expected29: List[(Int, Int)] = List((13, 5), (9, 2), (9, 0), (9, 18), (23, 22), (23, 21), (23, 20), (23, 19), (23, 18), (23, 17), (23, 31), (23, 39), (23, 47), (23, 55), (16, 1), (16, 10), (16, 26), (16, 33), (27, 3), (27, 11), (27, 20), (27, 19), (27, 18), (27, 30), (27, 29), (27, 28), (27, 26), (27, 36), (27, 35), (27, 34), (27, 43), (27, 41), (27, 51), (27, 48), (27, 59)).sorted;
            val fen29 = "8/1b3k1p/n6r/2Pq2Q1/p3R3/P4p2/1P3N1B/3RK3 b - - 0 53";
            LegalMoves.getAllLegalMoves(fen29).sorted should be(expected29)

            val expected30: List[(Int, Int)] = List((2, 11), (2, 9), (2, 20), (2, 16), (2, 29), (45, 30), (45, 28), (45, 39), (45, 51), (45, 62), (45, 60), (43, 16), (43, 25), (43, 36), (43, 34), (43, 44), (43, 42), (43, 41), (43, 52), (43, 51), (43, 50), (43, 61), (43, 59), (43, 57), (49, 41), (49, 54), (49, 53), (49, 52), (49, 51), (49, 50), (49, 48), (49, 57), (58, 59), (58, 57), (38, 31), (37, 30), (37, 28), (35, 28), (37, 29), (55, 47), (55, 39)).sorted;
            val fen30 = "1nB5/4br2/7k/p2pp1pp/p2PbPP1/1p1QpN2/1R5P/2K5 w - - 6 66";
            LegalMoves.getAllLegalMoves(fen30).sorted should be(expected30)

            val expected31: List[(Int, Int)] = List((8, 1), (8, 0), (8, 9), (8, 17), (8, 16), (50, 2), (50, 10), (50, 18), (50, 26), (50, 34), (50, 42), (50, 52), (50, 51), (50, 49), (50, 48), (50, 58), (62, 54), (62, 63), (62, 61), (62, 60), (62, 59), (62, 58), (62, 57), (62, 56), (28, 36), (40, 48)).sorted;
            val fen31 = "8/k7/8/3Pp2p/7K/pP6/2r1R1P1/6r1 b - - 3 70";
            LegalMoves.getAllLegalMoves(fen31).sorted should be(expected31)

            val expected32: List[(Int, Int)] = List((59, 52), (59, 50), (59, 60), (59, 58)).sorted;
            val fen32 = "8/6R1/6P1/8/3K4/8/8/2Bk4 b - - 22 164";
            LegalMoves.getAllLegalMoves(fen32).sorted should be(expected32)

            val expected33: List[(Int, Int)] = List((34, 6), (34, 13), (34, 20), (34, 27), (34, 25), (34, 43), (34, 52), (34, 61), (33, 18), (33, 27), (33, 43), (33, 50), (33, 48), (45, 5), (45, 13), (45, 21), (45, 29), (45, 37), (45, 47), (45, 46), (45, 44), (45, 43), (45, 42), (45, 53), (45, 61), (49, 40), (49, 50), (49, 48), (49, 56), (58, 37), (58, 44), (58, 51), (57, 56), (23, 14), (16, 9), (23, 15)).sorted;
            val fen33 = "3r4/pb1p2r1/P1n3PP/4k1P1/1NBp4/1P3R2/1K6/1RB3b1 w - - 3 47";
            LegalMoves.getAllLegalMoves(fen33).sorted should be(expected33)

            val expected34: List[(Int, Int)] = List((7, 15), (7, 23), (6, 12), (6, 23), (6, 21), (1, 18), (13, 5), (13, 4), (13, 14), (13, 12), (13, 22), (13, 21), (13, 20), (19, 5), (19, 12), (19, 10), (19, 23), (19, 22), (19, 21), (19, 20), (19, 18), (19, 17), (19, 27), (19, 35), (19, 43), (19, 51), (19, 59), (16, 0), (16, 8), (16, 17), (28, 14), (28, 21), (28, 37), (28, 35), (28, 42), (29, 36), (38, 47), (31, 39), (26, 34), (25, 33), (24, 32)).sorted;
            val fen34 = "1n4nr/3p1k2/rN1q4/ppp1bp1p/4PPp1/2Q3PB/PPP1K2P/R1BN3R b - - 5 21";
            LegalMoves.getAllLegalMoves(fen34).sorted should be(expected34)

            val expected35: List[(Int, Int)] = List((1, 4), (1, 3), (1, 2), (1, 0), (1, 10), (1, 9), (1, 8), (1, 19), (1, 17), (1, 28), (1, 25), (1, 37), (1, 33), (1, 46), (1, 55), (49, 34), (49, 43), (49, 59), (62, 54), (62, 63), (62, 61), (60, 61), (60, 59), (57, 59), (57, 58), (57, 56), (31, 23), (32, 24), (47, 39), (41, 33)).sorted;
            val fen35 = "1Q2r1nk/8/8/5p1P/P3p3/1P5P/1N4r1/1R2K1R1 w - - 2 64";
            LegalMoves.getAllLegalMoves(fen35).sorted should be(expected35)

            val expected36: List[(Int, Int)] = List((30, 23), (30, 22), (30, 31)).sorted;
            val fen36 = "8/5R2/8/1P2P1k1/4R3/K6P/P7/8 b - - 6 88";
            LegalMoves.getAllLegalMoves(fen36).sorted should be(expected36)

            val expected37: List[(Int, Int)] = List((24, 25), (24, 32), (45, 5), (45, 13), (45, 21), (45, 29), (45, 37), (45, 47), (45, 46), (45, 44), (45, 43), (45, 42), (45, 41), (45, 40), (45, 53), (45, 61)).sorted;
            val fen37 = "8/2k5/3q4/K7/1n6/5R2/8/8 w - - 50 160";
            LegalMoves.getAllLegalMoves(fen37).sorted should be(expected37)

            val expected38: List[(Int, Int)] = List((27, 3), (27, 11), (27, 19), (27, 31), (27, 30), (27, 29), (27, 28), (27, 26), (27, 25), (27, 24), (27, 35), (27, 43), (27, 51), (27, 59), (57, 50), (57, 49), (57, 48), (57, 58), (57, 56), (37, 45)).sorted;
            val fen38 = "8/8/8/3r4/2K2p2/8/8/1k6 b - - 7 242";
            LegalMoves.getAllLegalMoves(fen38).sorted should be(expected38)

            val expected39: List[(Int, Int)] = List((19, 5), (19, 1), (19, 12), (19, 10), (19, 28), (19, 26), (19, 37), (19, 33), (19, 46), (19, 40), (19, 55), (59, 51), (59, 60), (59, 58), (38, 30), (32, 24)).sorted;
            val fen39 = "6qk/8/3B4/3r4/P5P1/3p4/8/3K4 w - - 2 88";
            LegalMoves.getAllLegalMoves(fen39).sorted should be(expected39)

            val expected40: List[(Int, Int)] = List((34, 27), (34, 26), (34, 35), (34, 33), (34, 42), (34, 41)).sorted;
            val fen40 = "2N4r/8/2B5/7k/2K5/8/8/5q2 w - - 10 164";
            LegalMoves.getAllLegalMoves(fen40).sorted should be(expected40)


            val expected41: List[(Int, Int)] = List((18, 0), (18, 11), (18, 9), (18, 27), (18, 25), (18, 36), (18, 32), (18, 45), (18, 54), (18, 63), (29, 22), (29, 21), (29, 20), (29, 30), (29, 28), (29, 36), (50, 35), (50, 44), (50, 40), (50, 60), (50, 56), (33, 41), (49, 57), (49, 57), (49, 57), (49, 57)).sorted.distinct;
            val fen41 = "8/3R4/2b2P2/5k2/1p6/6K1/1pn5/8 b - - 21 127";
            LegalMoves.getAllLegalMoves(fen41).sorted should be(expected41)



            val expected42: List[(Int, Int)] = List((14, 7), (14, 6), (14, 5), (14, 15), (14, 13), (14, 23), (14, 22), (14, 21)).sorted;
            val fen42 = "8/6k1/8/6p1/6P1/3N4/4R3/5K2 b - - 94 204";
            LegalMoves.getAllLegalMoves(fen42).sorted should be(expected42)

            val expected43: List[(Int, Int)] = List((6, 21), (3, 4), (3, 11), (3, 10), (3, 17), (2, 11), (15, 7), (12, 4), (12, 11), (12, 21), (24, 0), (24, 8), (24, 16), (24, 30), (24, 29), (24, 28), (24, 27), (24, 26), (24, 25), (24, 32), (24, 40), (24, 48), (23, 30), (14, 22), (13, 21), (20, 28), (18, 26), (13, 29)).sorted;
            val fen43 = "2bq1bn1/1p2kppr/1Pppp2p/r5NP/1BP1PPP1/3P4/R1K5/5BQR b - - 8 26";
            LegalMoves.getAllLegalMoves(fen43).sorted should be(expected43)

            val expected44: List[(Int, Int)] = List((46, 1), (46, 10), (46, 19), (46, 28), (46, 39), (46, 37), (46, 55), (46, 53), (46, 60), (40, 33), (40, 32), (40, 41), (40, 49), (40, 48)).sorted;
            val fen44 = "8/8/8/6q1/8/K3k1B1/8/8 w - - 46 197";
            LegalMoves.getAllLegalMoves(fen44).sorted should be(expected44)


            val expected45: List[(Int, Int)] = List((12, 5), (12, 4), (12, 13), (12, 21), (12, 20), (43, 28), (43, 26), (43, 37), (43, 33), (43, 53), (43, 49), (43, 60), (43, 58), (48, 56), (48, 56), (48, 56), (48, 56)).sorted.distinct;
            val fen45 = "8/2K1k3/8/8/8/3n4/p7/8 b - - 17 159";
            LegalMoves.getAllLegalMoves(fen45).sorted should be(expected45)



            val expected46: List[(Int, Int)] = List((6, 12), (6, 21), (3, 2), (3, 12), (1, 11), (1, 18), (8, 0), (23, 5), (23, 14), (34, 13), (34, 20), (34, 27), (34, 25), (34, 43), (34, 41), (34, 48), (55, 28), (55, 37), (55, 47), (55, 46), (55, 54), (55, 63), (55, 62), (10, 17), (19, 28), (45, 54), (45, 52), (10, 18), (19, 27), (16, 24), (30, 38), (45, 53), (10, 26)).sorted;
            val fen46 = "1n1k2nr/rpp4p/pP1p3b/4P1p1/Q1b1P3/B1NP1p1P/P3N1Pq/R4K1R b - - 0 22";
            LegalMoves.getAllLegalMoves(fen46).sorted should be(expected46)

            val expected47: List[(Int, Int)] = List((14, 7), (14, 6), (14, 5), (14, 13), (14, 23), (14, 21), (28, 4), (28, 12), (28, 20), (28, 31), (28, 30), (28, 29), (28, 27), (28, 26), (28, 25), (28, 24), (28, 36), (28, 44), (28, 52), (28, 60), (61, 46), (61, 44), (61, 55), (61, 51), (33, 41), (47, 55)).sorted;
            val fen47 = "8/6k1/8/4r3/1p5K/7p/2B5/5n2 b - - 9 100";
            LegalMoves.getAllLegalMoves(fen47).sorted should be(expected47)

            val expected48: List[(Int, Int)] = List((23, 5), (23, 14), (23, 30), (23, 37), (23, 44), (23, 51), (23, 58), (62, 55), (62, 53), (62, 61)).sorted;
            val fen48 = "8/8/2b4B/8/7p/8/8/1k4K1 w - - 14 172";
            LegalMoves.getAllLegalMoves(fen48).sorted should be(expected48)

            val expected49: List[(Int, Int)] = List((7, 6), (7, 5), (7, 4), (7, 3), (7, 2), (7, 1), (7, 0), (7, 15), (13, 6), (13, 5), (13, 4), (13, 14), (13, 12), (10, 2), (10, 12), (10, 11), (10, 18), (23, 6), (23, 29), (23, 38), (17, 2), (17, 0), (17, 11), (17, 27), (17, 34), (17, 32), (30, 39), (30, 37), (30, 44), (30, 51), (30, 58), (41, 25), (41, 34), (41, 33), (41, 32), (41, 42), (41, 40), (41, 50), (41, 49), (41, 48), (41, 57), (21, 29)).sorted;
            val fen49 = "7r/1pr2k2/1n1p1ppn/N1pPp1b1/2P1P2P/PqBR1R2/2Q5/2N2B1K b - - 6 45";
            LegalMoves.getAllLegalMoves(fen49).sorted should be(expected49)

            val expected50: List[(Int, Int)] = List((30, 23), (30, 21), (30, 37), (30, 44), (42, 27), (42, 36), (42, 32), (42, 48), (42, 59), (42, 57), (40, 8), (40, 16), (40, 24), (40, 32), (40, 41), (40, 48), (40, 56), (51, 37), (51, 44), (51, 43), (51, 59), (51, 58), (63, 47), (63, 55), (62, 47), (62, 45), (60, 53), (60, 59), (29, 20), (25, 18), (25, 17), (39, 31), (54, 46), (52, 44), (54, 38), (52, 36)).sorted;
            val fen50 = "rnb1k1nr/pp1p2pp/2p1pp2/1P1q1PB1/3P3P/R1N5/2PQP1P1/4KBNR w Kkq - 4 14";
            LegalMoves.getAllLegalMoves(fen50).sorted should be(expected50)

            val expected51: List[(Int, Int)] = List((47, 38), (47, 46), (47, 55), (47, 54), (43, 15), (43, 22), (43, 16), (43, 29), (43, 25), (43, 36), (43, 34), (43, 52), (43, 61), (63, 55), (63, 62), (63, 61), (63, 60), (63, 59), (63, 58), (63, 57), (63, 56)).sorted;
            val fen51 = "2k5/8/8/6p1/8/2pB3K/2P5/7R w - - 3 98";
            LegalMoves.getAllLegalMoves(fen51).sorted should be(expected51)

            val expected52: List[(Int, Int)] = List((22, 7), (22, 5), (22, 12), (22, 28), (22, 39), (22, 37), (47, 30), (47, 37), (47, 53), (47, 62), (46, 39), (46, 37), (46, 45), (46, 55), (46, 54), (46, 53), (56, 28), (56, 35), (56, 42), (56, 49), (56, 48), (56, 57), (21, 29), (34, 42), (40, 48)).sorted;
            val fen52 = "8/8/5pn1/1K6/2p5/p4Pkn/8/qNR5 b - - 3 68";
            LegalMoves.getAllLegalMoves(fen52).sorted should be(expected52)

            val expected53: List[(Int, Int)] = List((6, 7), (6, 5), (6, 15), (6, 14), (6, 13), (23, 13), (23, 29), (23, 38), (47, 2), (47, 11), (47, 20), (47, 29), (47, 38), (47, 54), (47, 61)).sorted;
            val fen53 = "6k1/8/7n/8/8/7b/8/2K5 b - - 25 137";
            LegalMoves.getAllLegalMoves(fen53).sorted should be(expected53)

            val expected54: List[(Int, Int)] = List((13, 6), (13, 4), (13, 14), (13, 22), (13, 21), (13, 20), (18, 2), (18, 10), (18, 19), (18, 17), (18, 26), (18, 34), (18, 42), (18, 50), (18, 58), (25, 33)).sorted;
            val fen54 = "8/5k2/p1rB4/Pp6/8/8/5B2/1K6 b - - 16 133";
            LegalMoves.getAllLegalMoves(fen54).sorted should be(expected54)

            val expected55: List[(Int, Int)] = List((34, 27), (34, 26), (34, 25), (34, 33), (34, 42), (34, 41), (50, 42), (50, 55), (50, 54), (50, 53), (50, 52), (50, 51), (50, 49), (50, 48), (50, 58)).sorted;
            val fen55 = "8/4R3/5P2/8/2k5/4K3/2r5/8 b - - 22 160";
            LegalMoves.getAllLegalMoves(fen55).sorted should be(expected55)

            val expected56: List[(Int, Int)] = List((7, 14), (4, 6), (4, 5), (3, 2), (9, 1), (9, 8), (9, 17), (9, 25), (9, 33), (9, 41), (9, 49), (9, 57), (21, 6), (21, 15), (21, 11), (21, 31), (21, 27), (21, 36), (38, 23), (38, 28), (38, 44), (38, 55), (38, 53), (34, 6), (34, 13), (34, 20), (34, 16), (34, 27), (34, 25), (34, 43), (34, 41), (34, 48), (30, 37), (12, 20), (32, 40), (12, 28)).sorted;
            val fen56 = "N2kr2b/1rp1p3/2P2n2/5pp1/p1b2Pnp/3P3P/4P1PR/1NB1KB2 b - - 7 41";
            LegalMoves.getAllLegalMoves(fen56).sorted should be(expected56)

            val expected57: List[(Int, Int)] = List((7, 15), (6, 21), (5, 14), (2, 9), (0, 1), (0, 8), (24, 9), (24, 18), (24, 34), (24, 41), (25, 34), (13, 21), (12, 20), (11, 19), (10, 18), (23, 31), (30, 38), (25, 33), (13, 29), (12, 28), (11, 27), (10, 26)).sorted;
            val fen57 = "r1bqkbnr/2pppp2/p6p/np4p1/2PP4/1PQ4P/P3PPP1/RNB1KBNR b KQkq - 1 7";
            LegalMoves.getAllLegalMoves(fen57).sorted should be(expected57)

            val expected58: List[(Int, Int)] = List((47, 30), (47, 37), (62, 54), (62, 63), (61, 25), (61, 34), (61, 43), (61, 54), (61, 52), (60, 52), (59, 31), (59, 38), (59, 32), (59, 45), (59, 41), (59, 52), (59, 50), (58, 40), (58, 49), (57, 40), (26, 19), (26, 18), (36, 28), (46, 38), (42, 34), (53, 45), (51, 43), (48, 40), (53, 37), (51, 35), (48, 32)).sorted;
            val fen58 = "rnb1kb1r/2q1pppp/3p3n/ppP5/4P3/2P3PN/P2P1P1P/RNBQKBR1 w Qkq - 0 9";
            LegalMoves.getAllLegalMoves(fen58).sorted should be(expected58)

            val expected59: List[(Int, Int)] = List((15, 6), (15, 22), (15, 29), (15, 36), (15, 43), (15, 50), (15, 57), (17, 10), (17, 8), (17, 18), (17, 26), (25, 30), (25, 29), (25, 28), (25, 27), (25, 26), (25, 24), (25, 33), (25, 41), (25, 49), (25, 57), (31, 38), (39, 47)).sorted;
            val fen59 = "2B5/7b/1k2P3/1r5p/K4RPp/8/8/8 b - - 1 72";
            LegalMoves.getAllLegalMoves(fen59).sorted should be(expected59)

            val expected60: List[(Int, Int)] = List((9, 1), (9, 12), (9, 11), (9, 10), (9, 8), (9, 17), (9, 25), (9, 33), (9, 41), (9, 49), (9, 57), (52, 44), (52, 43), (52, 51), (52, 60), (52, 59), (45, 38), (45, 37)).sorted;
            val fen60 = "8/1R2r3/2p1k3/8/6rp/5P2/4KPb1/8 w - - 5 95";
            LegalMoves.getAllLegalMoves(fen60).sorted should be(expected60)

            val expected61: List[(Int, Int)] = List((29, 15), (29, 13), (29, 11), (29, 22), (29, 21), (29, 20), (29, 31), (29, 30), (29, 28), (29, 38), (29, 37), (29, 36), (29, 47), (29, 43), (29, 50), (45, 30), (45, 28), (45, 39), (45, 35), (45, 62), (63, 62), (60, 59), (57, 42), (57, 40), (55, 47), (54, 46), (52, 44), (51, 43), (49, 41), (48, 40), (55, 39), (54, 38), (52, 36), (51, 35), (49, 33), (48, 32)).sorted;
            val fen61 = "1nbqkbnr/1p1p1ppp/r7/p1p1pQ2/2P5/5N2/PP1PPPPP/RNB1KB1R w KQk - 0 5";
            LegalMoves.getAllLegalMoves(fen61).sorted should be(expected61)

            val expected62: List[(Int, Int)] = List((1, 2), (1, 0), (1, 10), (1, 9), (1, 8), (26, 5), (26, 12), (26, 8), (26, 19), (26, 17), (26, 35), (26, 33), (26, 44), (26, 40), (26, 53), (26, 62)).sorted;
            val fen62 = "1K6/8/8/2B2k2/6b1/8/8/8 w - - 137 183";
            LegalMoves.getAllLegalMoves(fen62).sorted should be(expected62)

            val expected63: List[(Int, Int)] = List((7, 6), (7, 5), (7, 15), (4, 5), (4, 3), (4, 13), (4, 12), (10, 3), (10, 2), (10, 1), (10, 15), (10, 14), (10, 13), (10, 12), (10, 11), (10, 19), (10, 18), (10, 17), (10, 26), (10, 24), (10, 34), (9, 2), (9, 0), (9, 18), (9, 16), (8, 0), (8, 16), (8, 24), (49, 40), (49, 58), (49, 56), (27, 36), (42, 51), (23, 31), (27, 35), (42, 50)).sorted;
            val fen63 = "4kN1r/rbq5/5p1p/3ppP2/p3P1p1/P1p3P1/Rb1PK2R/1BB5 b - - 1 29";
            LegalMoves.getAllLegalMoves(fen63).sorted should be(expected63)

            val expected64: List[(Int, Int)] = List((10, 4), (10, 0), (10, 20), (10, 16), (10, 27), (10, 25), (21, 14), (21, 12), (21, 22), (21, 29), (28, 4), (28, 12), (28, 20), (28, 31), (28, 30), (28, 29), (28, 27), (28, 36), (28, 44), (47, 7), (47, 15), (47, 23), (47, 31), (47, 39), (47, 46), (47, 45), (47, 44), (47, 55), (47, 63), (24, 32), (38, 46)).sorted;
            val fen64 = "8/2n2p2/5k2/p2Pr3/6p1/2K1B2r/8/8 b - - 5 112";
            LegalMoves.getAllLegalMoves(fen64).sorted should be(expected64)

            val expected65: List[(Int, Int)] = List((20, 12), (20, 23), (20, 22), (20, 21), (20, 19), (20, 28), (40, 41), (40, 48), (54, 46), (54, 45), (54, 55), (54, 63), (54, 62), (54, 61), (53, 38), (53, 43), (53, 63), (53, 59), (57, 51), (56, 49), (30, 23), (26, 19), (26, 17), (30, 22), (26, 18), (39, 31)).sorted;
            val fen65 = "3k4/1b2bp2/rq1pR2n/r1P3P1/P1p1P2P/R1P4P/5NK1/BN6 w - - 1 45";
            LegalMoves.getAllLegalMoves(fen65).sorted should be(expected65)

            val expected66: List[(Int, Int)] = List((47, 2), (47, 11), (47, 20), (47, 29), (47, 38), (47, 54), (47, 61), (42, 27), (42, 25), (42, 36), (42, 32), (42, 48), (42, 59), (42, 57), (52, 36), (52, 44), (52, 55), (52, 54), (52, 53), (52, 51), (52, 50), (52, 49), (52, 48), (60, 61), (31, 23)).sorted;
            val fen66 = "1r3q2/4k3/p2r4/7P/3pn3/1qN2b1B/4R3/3bK3 w - - 4 76";
            LegalMoves.getAllLegalMoves(fen66).sorted should be(expected66)

            val expected67: List[(Int, Int)] = List((2, 11), (2, 9), (2, 20), (36, 21), (36, 19), (36, 30), (36, 26), (36, 46), (36, 42), (36, 53), (36, 51), (59, 52), (59, 60), (59, 58), (43, 35), (50, 42), (50, 34)).sorted;
            val fen67 = "2B5/1bk5/p4b2/Pp3P2/4N3/3Pp3/2P5/3K4 w - - 6 84";
            LegalMoves.getAllLegalMoves(fen67).sorted should be(expected67)

            val expected68: List[(Int, Int)] = List((11, 4), (11, 2), (11, 18), (11, 25), (11, 32), (47, 39), (47, 38), (47, 46), (47, 55), (47, 54), (57, 42), (57, 40), (57, 51), (20, 12)).sorted;
            val fen68 = "2r5/k2B4/1b2P3/8/8/3n3K/8/1N6 w - - 3 101";
            LegalMoves.getAllLegalMoves(fen68).sorted should be(expected68)

            val expected69: List[(Int, Int)] = List((7, 6), (7, 15), (7, 14), (42, 14), (42, 21), (42, 28), (42, 24), (42, 35), (42, 33), (42, 51), (42, 49), (42, 60), (42, 56), (63, 15), (63, 23), (63, 31), (63, 39), (63, 47), (63, 55), (63, 62), (63, 61), (63, 60), (63, 59), (63, 58), (63, 57), (63, 56)).sorted;
            val fen69 = "7k/8/8/5K2/8/2b5/8/7r b - - 21 245";
            LegalMoves.getAllLegalMoves(fen69).sorted should be(expected69)

            val expected70: List[(Int, Int)] = List((7, 6), (4, 3), (2, 9), (2, 16), (1, 16), (23, 6), (23, 29), (23, 38), (24, 3), (24, 10), (24, 17), (24, 16), (24, 33), (24, 32), (24, 42), (24, 40), (24, 51), (24, 48), (25, 34), (14, 22), (13, 21), (12, 20), (11, 19), (8, 16), (18, 26), (25, 33), (14, 30), (13, 29), (12, 28), (11, 27)).sorted;
            val fen70 = "rnb1kb1r/p2ppppp/2p4n/qp6/2P4P/3P2P1/PP1QPP2/RN1K1BNR b kq - 1 7";
            LegalMoves.getAllLegalMoves(fen70).sorted should be(expected70)

            val expected71: List[(Int, Int)] = List((11, 4), (11, 2), (11, 20), (11, 18), (11, 29), (11, 38), (11, 47), (44, 23), (44, 30), (44, 37), (44, 51), (53, 54), (53, 52), (53, 62), (53, 60), (48, 32), (48, 40), (48, 52), (48, 51), (48, 50), (48, 49), (48, 56), (58, 43), (58, 41), (58, 52), (25, 17), (45, 37)).sorted;
            val fen71 = "8/r2B2k1/8/1P1p4/p2P3p/4BP2/R4K1n/2N5 w - - 0 59";
            LegalMoves.getAllLegalMoves(fen71).sorted should be(expected71)

            val expected72: List[(Int, Int)] = List((7, 6), (7, 15), (7, 23), (7, 31), (3, 4), (2, 9), (1, 0), (1, 9), (1, 17), (1, 25), (1, 33), (1, 41), (1, 49), (1, 57), (12, 4), (12, 21), (12, 19), (55, 38), (55, 45), (55, 61), (11, 18), (14, 22), (13, 21), (11, 19), (20, 28), (16, 24), (14, 30), (13, 29), (11, 27)).sorted;
            val fen72 = "1rbq1b1r/p1ppkpp1/p1P1p3/7P/2PP4/7P/P3PP1n/RNB1KBNR b KQ - 0 10";
            LegalMoves.getAllLegalMoves(fen72).sorted should be(expected72)

            val expected73: List[(Int, Int)] = List((25, 17), (25, 24), (28, 52), (26, 34)).sorted;
            val fen73 = "r7/4n1N1/3p4/1kpPr3/pN6/8/1R2Q3/2B2K2 b - - 6 76";
            LegalMoves.getAllLegalMoves(fen73).sorted should be(expected73)

            val expected74: List[(Int, Int)] = List((23, 22), (23, 31), (24, 16)).sorted;
            val fen74 = "2k5/3r4/5p1K/P3pPp1/4P3/2r5/1p6/8 w - - 10 69";
            LegalMoves.getAllLegalMoves(fen74).sorted should be(expected74)

            val expected75: List[(Int, Int)] = List((5, 6), (5, 4), (5, 14), (5, 13), (5, 12), (15, 6), (15, 22), (15, 29), (15, 36), (15, 43), (15, 50), (15, 57), (21, 7), (21, 3), (21, 14), (21, 13), (21, 12), (21, 23), (21, 22), (21, 20), (21, 19), (21, 18), (21, 17), (21, 30), (21, 29), (21, 28), (21, 37), (21, 45), (21, 53), (21, 61), (16, 0), (16, 8), (16, 20), (16, 19), (16, 18), (16, 17), (16, 24)).sorted;
            val fen75 = "5k2/7b/r4q2/P3B3/7p/7P/8/6K1 b - - 4 83";
            LegalMoves.getAllLegalMoves(fen75).sorted should be(expected75)

            val expected76: List[(Int, Int)] = List((7, 6), (7, 15), (5, 14), (4, 3), (4, 11), (2, 11), (2, 20), (2, 29), (2, 38), (1, 11), (9, 0), (9, 11), (9, 10), (9, 17), (8, 0), (31, 14), (31, 21), (31, 37), (31, 46), (19, 26), (13, 21), (12, 20), (22, 30), (19, 27), (16, 24), (25, 33), (13, 29), (12, 28)).sorted;
            val fen76 = "1nb1kb1r/rq2pp2/p1pp2pp/1pP4n/3PNPP1/7P/PP1QP1R1/RNB1KB2 b Qk - 1 12";
            LegalMoves.getAllLegalMoves(fen76).sorted should be(expected76)

            val expected77: List[(Int, Int)] = List((4, 7), (4, 6), (4, 5), (4, 3), (4, 2), (4, 1), (4, 0), (4, 13), (4, 12), (4, 11), (4, 22), (4, 20), (4, 18), (4, 31), (4, 28), (4, 25), (4, 36), (4, 32), (4, 44), (61, 16), (61, 25), (61, 34), (61, 43), (61, 54), (61, 53), (61, 52), (61, 63), (61, 62), (61, 60), (59, 52), (59, 50)).sorted;
            val fen77 = "4Q3/2k5/8/7r/1p6/4b3/5pr1/3K1Q2 w - - 9 123";
            LegalMoves.getAllLegalMoves(fen77).sorted should be(expected77)

            val expected78: List[(Int, Int)] = List((20, 6), (20, 2), (20, 13), (20, 11), (20, 29), (20, 27), (20, 38), (20, 47), (46, 1), (46, 10), (46, 19), (46, 28), (46, 39), (46, 37), (46, 53), (46, 60), (55, 47), (55, 54), (55, 63), (55, 62), (56, 0), (56, 8), (56, 16), (56, 24), (56, 32), (56, 40), (56, 49), (56, 48), (56, 63), (56, 62), (56, 61), (56, 60), (56, 59), (56, 58), (56, 57)).sorted;
            val fen78 = "7b/4k3/4B3/2p5/2P5/2P1p1B1/7K/Q7 w - - 14 95";
            LegalMoves.getAllLegalMoves(fen78).sorted should be(expected78)

            val expected79: List[(Int, Int)] = List((38, 20), (38, 31), (38, 29), (38, 47), (38, 45), (38, 52), (46, 31), (46, 29), (46, 52), (46, 63), (49, 58), (49, 56), (61, 45), (61, 53), (61, 63), (61, 62), (60, 52), (60, 51), (59, 51), (59, 58), (59, 57), (59, 56), (37, 30), (37, 28), (37, 29), (43, 35), (42, 34), (40, 32)).sorted;
            val fen79 = "b2r1b2/p4k2/4q2p/4p1r1/4PPB1/P1PP2Np/1B6/3RKR1n w - - 2 46";
            LegalMoves.getAllLegalMoves(fen79).sorted should be(expected79)

            val expected80: List[(Int, Int)] = List((45, 30), (45, 39), (45, 35), (45, 51), (52, 61), (52, 59), (62, 47), (60, 51), (60, 61), (60, 59), (58, 23), (58, 30), (58, 37), (58, 44), (58, 40), (58, 51), (58, 49), (56, 57), (28, 21), (28, 20), (46, 38), (43, 35), (41, 33), (55, 47), (50, 42), (48, 40), (55, 39), (50, 34), (48, 32)).sorted;
            val fen80 = "2q1kbnr/prppp2p/b4pp1/1p2Pn2/8/1P1P1NP1/P1P1BP1P/R1B1K1NR w KQk - 0 11";
            LegalMoves.getAllLegalMoves(fen80).sorted should be(expected80)

            val expected81: List[(Int, Int)] = List((3, 13), (3, 9), (3, 20), (3, 18), (11, 4), (11, 2), (11, 20), (11, 18), (11, 29), (11, 25), (11, 38), (11, 32), (11, 47), (57, 50), (57, 49), (57, 48), (57, 58), (57, 56)).sorted;
            val fen81 = "3n4/3b4/3K4/8/7B/8/8/1k6 b - - 59 178";
            LegalMoves.getAllLegalMoves(fen81).sorted should be(expected81)

            val expected82: List[(Int, Int)] = List((62, 47), (62, 45), (60, 51), (59, 51), (58, 23), (58, 30), (58, 37), (58, 44), (58, 51), (57, 42), (57, 51), (56, 48), (43, 35), (40, 32), (55, 47), (54, 46), (53, 45), (52, 44), (50, 42), (49, 41), (55, 39), (54, 38), (53, 37), (52, 36), (50, 34), (49, 33)).sorted;
            val fen82 = "rnbqkb1r/ppppp1pp/7n/5p2/8/P2P4/1PP1PPPP/RNBQKBNR w KQkq - 1 3";
            LegalMoves.getAllLegalMoves(fen82).sorted should be(expected82)

            val expected83: List[(Int, Int)] = List((6, 15), (6, 13), (6, 20), (6, 27), (6, 34), (6, 41), (6, 48), (23, 7), (23, 15), (23, 22), (23, 21), (23, 20), (23, 19), (23, 18), (23, 17), (23, 16), (23, 31), (23, 39), (23, 47), (23, 55), (23, 63), (62, 55), (62, 54), (62, 53), (62, 63), (62, 61)).sorted;
            val fen83 = "2r3B1/8/7R/6k1/8/8/8/6K1 w - - 80 178";
            LegalMoves.getAllLegalMoves(fen83).sorted should be(expected83)

            val expected84: List[(Int, Int)] = List((48, 49), (48, 57), (48, 56)).sorted;
            val fen84 = "8/8/8/8/7k/4r3/K7/8 w - - 103 213";
            LegalMoves.getAllLegalMoves(fen84).sorted should be(expected84)

            val expected85: List[(Int, Int)] = List((22, 6), (22, 14), (22, 23), (22, 21), (22, 20), (22, 19), (22, 18), (22, 17), (22, 16), (22, 30), (22, 38), (22, 46), (22, 54), (22, 62), (55, 47), (55, 54), (55, 63), (55, 62), (51, 24), (51, 37), (51, 33), (51, 44), (51, 42), (51, 60), (51, 58), (35, 27)).sorted;
            val fen85 = "5b2/8/6R1/5k2/3P1p1p/5P2/3B3K/8 w - - 16 91";
            LegalMoves.getAllLegalMoves(fen85).sorted should be(expected85)

            val expected86: List[(Int, Int)] = List((53, 46), (53, 45), (53, 44), (53, 54), (53, 52), (53, 62), (53, 61), (53, 60)).sorted;
            val fen86 = "8/8/p7/P7/8/8/k4K2/8 w - - 68 200";
            LegalMoves.getAllLegalMoves(fen86).sorted should be(expected86)

            val expected87: List[(Int, Int)] = List((6, 15), (6, 14), (6, 13), (4, 5), (4, 3), (4, 2), (4, 1), (4, 0), (12, 5), (12, 3), (12, 21), (12, 30), (12, 39), (11, 3), (11, 10), (11, 9), (11, 8), (29, 15), (29, 22), (29, 20), (29, 38), (29, 36), (27, 10), (27, 21), (27, 37), (27, 44), (27, 42), (33, 40), (17, 25), (26, 34), (33, 41)).sorted;
            val fen87 = "4r1k1/3rb1P1/1p1p4/2pn1b2/1p2BRP1/P3P3/2P5/3K1RN1 b - - 0 51";
            LegalMoves.getAllLegalMoves(fen87).sorted should be(expected87)

            val expected88: List[(Int, Int)] = List((7, 15), (6, 12), (5, 14), (5, 12), (4, 13), (4, 12), (4, 11), (3, 2), (3, 1), (3, 0), (3, 12), (3, 11), (9, 1), (9, 17), (33, 18), (33, 27), (33, 43), (33, 50), (33, 48), (25, 34), (10, 18), (23, 31), (21, 29), (20, 28), (19, 27), (10, 26)).sorted;
            val fen88 = "3qkbnr/prp5/b2ppp1p/1p4p1/1nP1N1P1/BP2NP2/PQ1PP2P/R3KBR1 b Qk - 0 17";
            LegalMoves.getAllLegalMoves(fen88).sorted should be(expected88)

            val expected89: List[(Int, Int)] = List((5, 14), (5, 12), (5, 19), (5, 26), (5, 33), (5, 40), (4, 3), (4, 12), (4, 20), (15, 7), (15, 6), (15, 14), (11, 1), (11, 21), (11, 17), (11, 26), (23, 22), (23, 21), (23, 20), (23, 19), (23, 18), (23, 17), (23, 16), (25, 1), (25, 9), (25, 18), (25, 17), (25, 16), (25, 26), (25, 24), (25, 34), (25, 33), (25, 32), (25, 41), (36, 21), (36, 19), (36, 30), (36, 26), (36, 46), (36, 42), (36, 53), (36, 51), (28, 35), (27, 34), (10, 18), (10, 26)).sorted;
            val fen89 = "3Nrb2/2pn3k/7r/1q1ppP1p/P1BPn2P/1P2Pp2/1N3P2/Q1R2K2 b - - 1 33";
            LegalMoves.getAllLegalMoves(fen89).sorted should be(expected89)

            val expected90: List[(Int, Int)] = List((39, 31), (39, 30), (39, 38), (39, 46), (36, 4), (36, 12), (36, 20), (36, 28), (36, 38), (36, 37), (36, 35), (36, 34), (36, 33), (36, 32), (36, 44), (36, 52), (36, 60), (47, 2), (47, 11), (47, 20), (47, 29), (47, 38), (47, 54), (47, 61)).sorted;
            val fen90 = "8/8/8/8/4r2k/7b/2K5/8 b - - 49 152";
            LegalMoves.getAllLegalMoves(fen90).sorted should be(expected90)

            val expected91: List[(Int, Int)] = List((7, 6), (7, 5), (7, 4), (7, 3), (7, 2), (7, 1), (7, 0), (7, 15), (7, 14), (7, 23), (7, 21), (7, 31), (7, 28), (7, 39), (7, 35), (7, 47), (7, 55), (7, 63), (44, 29), (44, 27), (44, 38), (44, 34), (44, 54), (44, 50), (44, 61), (44, 59), (42, 35), (42, 34), (42, 43), (42, 41), (42, 51), (42, 50), (42, 49)).sorted;
            val fen91 = "7q/8/3K4/8/p7/P1k1n3/8/8 b - - 25 131";
            LegalMoves.getAllLegalMoves(fen91).sorted should be(expected91)

            val expected92: List[(Int, Int)] = List((5, 14), (5, 23), (0, 1), (0, 8), (0, 16), (15, 7), (15, 14), (15, 23), (21, 6), (21, 27), (21, 38), (21, 36), (12, 20), (11, 19), (10, 18), (9, 17), (31, 39), (30, 38), (12, 28), (11, 27), (10, 26), (9, 25)).sorted;
            val fen92 = "r1bqkb2/1ppppp1r/5n2/p5pp/P7/2P2NPB/1PQPP2P/RNB1K2R b KQq - 4 9";
            LegalMoves.getAllLegalMoves(fen92).sorted should be(expected92)

            val expected93: List[(Int, Int)] = List((20, 13), (20, 11), (20, 21), (20, 19), (20, 29), (20, 27), (12, 27), (30, 27)).sorted;
            val fen93 = "5b2/4n3/bB2k1pR/1p1P2r1/2r2Pp1/P2P3B/R7/1Q2K3 b - - 0 42";
            LegalMoves.getAllLegalMoves(fen93).sorted should be(expected93)

            val expected94: List[(Int, Int)] = List((4, 5), (4, 3), (1, 11), (1, 18), (1, 16), (12, 15), (12, 14), (12, 13), (12, 11), (12, 10), (12, 9), (12, 8), (12, 20), (37, 22), (37, 20), (37, 31), (37, 27), (37, 47), (37, 43), (37, 54), (37, 52), (58, 43), (58, 41), (58, 52), (58, 48)).sorted;
            val fen94 = "1n2k3/4r3/3KP2p/2R4P/P4n2/8/8/B1n5 b - - 2 75";
            LegalMoves.getAllLegalMoves(fen94).sorted should be(expected94)

            val expected95: List[(Int, Int)] = List((6, 7), (6, 15), (6, 14), (23, 13), (23, 29), (23, 38)).sorted;
            val fen95 = "6k1/8/5R1n/6K1/6P1/8/8/8 b - - 6 133";
            LegalMoves.getAllLegalMoves(fen95).sorted should be(expected95)

            val expected96: List[(Int, Int)] = List((30, 23), (30, 22), (30, 21), (30, 31), (30, 29), (30, 39), (30, 38), (30, 37), (28, 7), (28, 1), (28, 14), (28, 10), (28, 21), (28, 19), (28, 37), (28, 35), (28, 46), (28, 42), (28, 55), (28, 49), (28, 56), (59, 44), (59, 42), (59, 53), (59, 49)).sorted;
            val fen96 = "5k2/8/8/4B1K1/8/8/8/3N4 w - - 41 196";
            LegalMoves.getAllLegalMoves(fen96).sorted should be(expected96)

            val expected97: List[(Int, Int)] = List((28, 20), (28, 27), (28, 37), (28, 36), (28, 35)).sorted;
            val fen97 = "8/8/6K1/4k3/2N5/B7/8/4n3 b - - 6 149";
            LegalMoves.getAllLegalMoves(fen97).sorted should be(expected97)

            val expected98: List[(Int, Int)] = List((14, 7), (14, 6), (14, 5), (14, 15), (14, 13), (14, 23), (14, 22), (14, 21), (11, 5), (11, 1), (11, 21), (11, 17), (11, 28), (11, 26)).sorted;
            val fen98 = "8/k2N2K1/5p2/5P2/8/8/7b/8 w - - 12 148";
            LegalMoves.getAllLegalMoves(fen98).sorted should be(expected98)

            val expected99: List[(Int, Int)] = List((4, 3), (4, 12), (10, 2), (10, 15), (10, 14), (10, 13), (10, 12), (10, 11), (10, 9), (10, 8), (10, 18), (26, 5), (26, 12), (26, 19), (26, 17), (26, 35), (26, 33), (26, 40), (44, 29), (44, 27), (44, 38), (44, 34), (44, 54), (44, 50), (44, 61), (44, 59), (22, 31), (39, 47), (45, 53)).sorted;
            val fen99 = "1N2k3/2r5/1P4p1/2b4Q/5RBp/4np2/8/6K1 b - - 7 89";
            LegalMoves.getAllLegalMoves(fen99).sorted should be(expected99)


        }
    }
}
