package com.taufufah.ehailing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Destination;
import com.taufufah.ehailing.model.Driver;
import com.taufufah.ehailing.model.Status;
import com.taufufah.ehailing.model.Vertex;
import com.taufufah.ehailing.repository.CustomerRepository;
import com.taufufah.ehailing.repository.DestinationRepository;
import com.taufufah.ehailing.repository.DriverRepository;
import com.taufufah.ehailing.repository.VertexRepository;
import com.taufufah.ehailing.service.UpdateService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableScheduling
public class EhailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhailingApplication.class, args);

    }

    @Bean
    CommandLineRunner demo(Neo4jClient neo4jClient, VertexRepository vertexRepository,
            DriverRepository driverRepository,
            CustomerRepository customerRepository, DestinationRepository destinationRepository,
            UpdateService updateService) {
        return args -> {
            vertexRepository.deleteAll();
            driverRepository.deleteAll();
            customerRepository.deleteAll();
            destinationRepository.deleteAll();
            //old map
//            vertexRepository.save(new Vertex(72, 130));
//            vertexRepository.save(new Vertex(71, 124));
//            vertexRepository.save(new Vertex(82, 113));
//            vertexRepository.save(new Vertex(93, 105));
//            vertexRepository.save(new Vertex(95, 102));
//            vertexRepository.save(new Vertex(105, 102));
//            vertexRepository.save(new Vertex(110, 105));
//            vertexRepository.save(new Vertex(122, 106));
//            vertexRepository.save(new Vertex(121, 114));
//            vertexRepository.save(new Vertex(121, 119));
//
//            vertexRepository.save(new Vertex(76, 131));
//            vertexRepository.save(new Vertex(77, 126));
//            vertexRepository.save(new Vertex(85, 117));
//            vertexRepository.save(new Vertex(77, 135));
//            vertexRepository.save(new Vertex(82, 138));
//            vertexRepository.save(new Vertex(83, 141));
//            vertexRepository.save(new Vertex(97, 116));
//            vertexRepository.save(new Vertex(108, 121));
//            vertexRepository.save(new Vertex(119, 126));
//            vertexRepository.save(new Vertex(123, 128));
//            vertexRepository.save(new Vertex(128, 130));
//            vertexRepository.save(new Vertex(130, 131));
//            vertexRepository.save(new Vertex(92, 141));
//            vertexRepository.save(new Vertex(102, 139));
//            vertexRepository.save(new Vertex(111, 136));
//            vertexRepository.save(new Vertex(118, 134));
//            vertexRepository.save(new Vertex(126, 134));
//
//            vertexRepository.connectVertex(76, 131, 72, 130);
//            vertexRepository.connectVertex(72, 130, 71, 124);
//            vertexRepository.connectVertex(71, 124, 82, 113);
//            vertexRepository.connectVertex(82, 113, 93, 105);
//            vertexRepository.connectVertex(93, 105, 95, 102);
//            vertexRepository.connectVertex(95, 102, 105, 102);
//            vertexRepository.connectVertex(105, 102, 110, 105);
//            vertexRepository.connectVertex(110, 105, 122, 106);
//            vertexRepository.connectVertex(122, 106, 121, 114);
//            vertexRepository.connectVertex(121, 114, 121, 119);
//            vertexRepository.connectVertex(121, 119, 119, 126);
//
//            vertexRepository.connectVertex(76, 131, 77, 126);
//            vertexRepository.connectVertex(77, 126, 85, 117);
//            vertexRepository.connectVertex(85, 117, 97, 116);
//            vertexRepository.connectVertex(97, 116, 108, 121);
//            vertexRepository.connectVertex(108, 121, 119, 126);
//
//            vertexRepository.connectVertex(76, 131, 77, 135);
//            vertexRepository.connectVertex(77, 135, 82, 138);
//            vertexRepository.connectVertex(82, 138, 83, 141);
//
//            vertexRepository.connectVertex(119, 126, 123, 128);
//            vertexRepository.connectVertex(123, 128, 128, 130);
//            vertexRepository.connectVertex(128, 130, 130, 131);
//
//            vertexRepository.connectVertex(83, 141, 92, 141);
//            vertexRepository.connectVertex(92, 141, 102, 139);
//            vertexRepository.connectVertex(102, 139, 111, 136);
//            vertexRepository.connectVertex(111, 136, 118, 134);
//            vertexRepository.connectVertex(118, 134, 126, 134);
//            vertexRepository.connectVertex(126, 134, 130, 131);

            //new map
            vertexRepository.save(new Vertex(1, 266));
            vertexRepository.save(new Vertex(13, 265));
            vertexRepository.save(new Vertex(37, 255));
            vertexRepository.save(new Vertex(44, 248));
            vertexRepository.save(new Vertex(63, 242));
            vertexRepository.save(new Vertex(71, 236));
            vertexRepository.save(new Vertex(77, 226));
            vertexRepository.save(new Vertex(91, 255));
            vertexRepository.save(new Vertex(103, 232)); //junction 1

            vertexRepository.save(new Vertex(114, 220)); //to porchini
            vertexRepository.save(new Vertex(116, 214));
            vertexRepository.save(new Vertex(127, 208));
            vertexRepository.save(new Vertex(132, 204));
            vertexRepository.save(new Vertex(133, 195));
            vertexRepository.save(new Vertex(141, 168));
            vertexRepository.save(new Vertex(146, 164));
            vertexRepository.save(new Vertex(151, 151));
            vertexRepository.save(new Vertex(143, 131));
            vertexRepository.save(new Vertex(145, 124));
            vertexRepository.save(new Vertex(163, 108));
            vertexRepository.save(new Vertex(173, 87));
            vertexRepository.save(new Vertex(185, 74));
            vertexRepository.save(new Vertex(192, 53));
            vertexRepository.save(new Vertex(187, 46));
            vertexRepository.save(new Vertex(170, 32));
            vertexRepository.save(new Vertex(148, 19));
            vertexRepository.save(new Vertex(128, 12));
            vertexRepository.save(new Vertex(118, 3)); //end

            vertexRepository.save(new Vertex(107, 239)); //to military base
            vertexRepository.save(new Vertex(108, 249));
            vertexRepository.save(new Vertex(113, 256));
            vertexRepository.save(new Vertex(185, 343)); //junction 2

            vertexRepository.save(new Vertex(194, 327)); //junction 2 right
            vertexRepository.save(new Vertex(199, 318));
            vertexRepository.save(new Vertex(218, 309));
            vertexRepository.save(new Vertex(233, 298));
            vertexRepository.save(new Vertex(243, 295));
            vertexRepository.save(new Vertex(274, 300));
            vertexRepository.save(new Vertex(296, 301));
            vertexRepository.save(new Vertex(308, 294));
            vertexRepository.save(new Vertex(324, 295));
            vertexRepository.save(new Vertex(328, 299));
            vertexRepository.save(new Vertex(329, 309));
            vertexRepository.save(new Vertex(334, 314));
            vertexRepository.save(new Vertex(338, 316));
            vertexRepository.save(new Vertex(373, 307));
            vertexRepository.save(new Vertex(378, 297));
            vertexRepository.save(new Vertex(384, 293));
            vertexRepository.save(new Vertex(395, 297));
            vertexRepository.save(new Vertex(400, 304));
            vertexRepository.save(new Vertex(402, 311));
            vertexRepository.save(new Vertex(408, 314));
            vertexRepository.save(new Vertex(421, 328));
            vertexRepository.save(new Vertex(423, 336));
            vertexRepository.save(new Vertex(426, 338));
            vertexRepository.save(new Vertex(434, 339));
            vertexRepository.save(new Vertex(439, 336));
            vertexRepository.save(new Vertex(447, 336)); //end

            vertexRepository.save(new Vertex(199, 368)); //junction 2 down
            vertexRepository.save(new Vertex(202, 386)); //junction 3

            vertexRepository.save(new Vertex(204, 440)); //junction 3 down + junction 4

            vertexRepository.save(new Vertex(197, 387)); //junction 3 left
            vertexRepository.save(new Vertex(190, 397));
            vertexRepository.save(new Vertex(179, 407));
            vertexRepository.save(new Vertex(166, 415));
            vertexRepository.save(new Vertex(154, 421));
            vertexRepository.save(new Vertex(140, 429));
            vertexRepository.save(new Vertex(128, 447));
            vertexRepository.save(new Vertex(127, 456));
            vertexRepository.save(new Vertex(130, 461));
            vertexRepository.save(new Vertex(151, 483));
            vertexRepository.save(new Vertex(171, 505));
            vertexRepository.save(new Vertex(180, 514));
            vertexRepository.save(new Vertex(187, 515));
            vertexRepository.save(new Vertex(214, 508));
            vertexRepository.save(new Vertex(222, 511));
            vertexRepository.save(new Vertex(236, 519));
            vertexRepository.save(new Vertex(247, 521));
            vertexRepository.save(new Vertex(292, 530));
            vertexRepository.save(new Vertex(308, 535));
            vertexRepository.save(new Vertex(322, 531));
            vertexRepository.save(new Vertex(358, 513));
            vertexRepository.save(new Vertex(378, 504));
            vertexRepository.save(new Vertex(395, 507));
            vertexRepository.save(new Vertex(412, 503));
            vertexRepository.save(new Vertex(441, 491));
            vertexRepository.save(new Vertex(451, 482));
            vertexRepository.save(new Vertex(464, 477));
            vertexRepository.save(new Vertex(480, 482));
            vertexRepository.save(new Vertex(496, 487));
            vertexRepository.save(new Vertex(514, 488));
            vertexRepository.save(new Vertex(532, 482));
            vertexRepository.save(new Vertex(541, 474));
            vertexRepository.save(new Vertex(550, 458));
            vertexRepository.save(new Vertex(559, 449));
            vertexRepository.save(new Vertex(583, 424));
            vertexRepository.save(new Vertex(585, 418));
            vertexRepository.save(new Vertex(582, 403));
            vertexRepository.save(new Vertex(573, 385));
            vertexRepository.save(new Vertex(566, 368));
            vertexRepository.save(new Vertex(562, 362));
            vertexRepository.save(new Vertex(560, 350));
            vertexRepository.save(new Vertex(558, 343));
            vertexRepository.save(new Vertex(447, 330));
            vertexRepository.save(new Vertex(547, 325));
            vertexRepository.save(new Vertex(537, 336));
            vertexRepository.save(new Vertex(521, 340));
            vertexRepository.save(new Vertex(510, 344));
            vertexRepository.save(new Vertex(484, 344)); //junction 5

            vertexRepository.save(new Vertex(209, 438)); //junction 4 right
            vertexRepository.save(new Vertex(238, 415));
            vertexRepository.save(new Vertex(245, 411));
            vertexRepository.save(new Vertex(249, 407));
            vertexRepository.save(new Vertex(261, 399));
            vertexRepository.save(new Vertex(263, 394));
            vertexRepository.save(new Vertex(270, 390));
            vertexRepository.save(new Vertex(279, 388));
            vertexRepository.save(new Vertex(297, 372));
            vertexRepository.save(new Vertex(302, 372));
            vertexRepository.save(new Vertex(339, 387));
            vertexRepository.save(new Vertex(357, 400));
            vertexRepository.save(new Vertex(366, 409));
            vertexRepository.save(new Vertex(383, 420));
            vertexRepository.save(new Vertex(390, 421));
            vertexRepository.save(new Vertex(410, 400));
            vertexRepository.save(new Vertex(455, 353));
            vertexRepository.save(new Vertex(469, 343)); //junction 5 (484,343) <- added

            vertexRepository.save(new Vertex(204, 470)); //junction 4 down
            vertexRepository.save(new Vertex(208, 476));
            vertexRepository.save(new Vertex(412, 476));
            vertexRepository.save(new Vertex(435, 472));
            vertexRepository.save(new Vertex(468, 464));
            vertexRepository.save(new Vertex(488, 475));
            vertexRepository.save(new Vertex(495, 472));
            vertexRepository.save(new Vertex(506, 458));
            vertexRepository.save(new Vertex(522, 418));
            vertexRepository.save(new Vertex(521, 402));
            vertexRepository.save(new Vertex(508, 370));
            vertexRepository.save(new Vertex(492, 358)); //junction 5 (484, 343) <- added

            vertexRepository.save(new Vertex(482, 318)); //junction 5 up
            vertexRepository.save(new Vertex(483, 203));
            vertexRepository.save(new Vertex(491, 194));
            vertexRepository.save(new Vertex(500, 182)); //junction 6

            vertexRepository.save(new Vertex(485, 175)); //junction 6 left
            vertexRepository.save(new Vertex(478, 177));
            vertexRepository.save(new Vertex(459, 168));
            vertexRepository.save(new Vertex(453, 156));
            vertexRepository.save(new Vertex(449, 143));
            vertexRepository.save(new Vertex(438, 143));
            vertexRepository.save(new Vertex(430, 147));
            vertexRepository.save(new Vertex(422, 156));
            vertexRepository.save(new Vertex(407, 159));
            vertexRepository.save(new Vertex(397, 156));
            vertexRepository.save(new Vertex(393, 143));
            vertexRepository.save(new Vertex(379, 134));
            vertexRepository.save(new Vertex(375, 128));
            vertexRepository.save(new Vertex(364, 120));
            vertexRepository.save(new Vertex(354, 126));
            vertexRepository.save(new Vertex(346, 127));
            vertexRepository.save(new Vertex(318, 122));
            vertexRepository.save(new Vertex(314, 124));
            vertexRepository.save(new Vertex(292, 124));
            vertexRepository.save(new Vertex(280, 123));
            vertexRepository.save(new Vertex(268, 127));
            vertexRepository.save(new Vertex(257, 128));
            vertexRepository.save(new Vertex(242, 137));
            vertexRepository.save(new Vertex(218, 135));
            vertexRepository.save(new Vertex(201, 128));
            vertexRepository.save(new Vertex(186, 133));
            vertexRepository.save(new Vertex(170, 134)); //end

            vertexRepository.save(new Vertex(508, 173)); //junction 6 up
            vertexRepository.save(new Vertex(516, 167));
            vertexRepository.save(new Vertex(536, 134)); //junction 7

            vertexRepository.save(new Vertex(537, 123)); //junction 7 up
            vertexRepository.save(new Vertex(532, 111));
            vertexRepository.save(new Vertex(502, 41));
            vertexRepository.save(new Vertex(489, 17));
            vertexRepository.save(new Vertex(489, 1)); //end

            vertexRepository.save(new Vertex(552, 140)); //junction 7 right
            vertexRepository.save(new Vertex(558, 145));
            vertexRepository.save(new Vertex(580, 152));
            vertexRepository.save(new Vertex(592, 154));
            vertexRepository.save(new Vertex(603, 159));
            vertexRepository.save(new Vertex(611, 159));
            vertexRepository.save(new Vertex(621, 154)); //junction 8

            vertexRepository.save(new Vertex(644, 158)); //junction 8 right
            vertexRepository.save(new Vertex(648, 168));
            vertexRepository.save(new Vertex(674, 195)); ///end

            vertexRepository.save(new Vertex(615, 147)); //junction 8 up
            vertexRepository.save(new Vertex(615, 137));
            vertexRepository.save(new Vertex(619, 132));
            vertexRepository.save(new Vertex(622, 123));
            vertexRepository.save(new Vertex(623, 118));
            vertexRepository.save(new Vertex(629, 116));
            vertexRepository.save(new Vertex(636, 121));
            vertexRepository.save(new Vertex(642, 122));
            vertexRepository.save(new Vertex(649, 128));
            vertexRepository.save(new Vertex(661, 125));
            vertexRepository.save(new Vertex(664, 119));
            vertexRepository.save(new Vertex(674, 110)); //end




            vertexRepository.connectVertex(1, 266, 13, 265); //start from left
            vertexRepository.connectVertex(13, 265, 37, 255);
            vertexRepository.connectVertex(37, 255, 44, 248);
            vertexRepository.connectVertex(44, 248, 63, 242);
            vertexRepository.connectVertex(63, 242, 71, 236);
            vertexRepository.connectVertex(71, 236, 91, 255);
            vertexRepository.connectVertex(91, 255, 103, 232); //junction 1

            vertexRepository.connectVertex(103, 232, 114, 220); //to porchini
            vertexRepository.connectVertex(114, 220, 116, 214);
            vertexRepository.connectVertex(116, 214, 127, 208);
            vertexRepository.connectVertex(127, 208, 132, 204);
            vertexRepository.connectVertex(132, 204, 133, 195);
            vertexRepository.connectVertex(133, 195, 141, 168);
            vertexRepository.connectVertex(141, 168, 146, 164);
            vertexRepository.connectVertex(146, 164, 151, 151);
            vertexRepository.connectVertex(151, 151, 143, 131);
            vertexRepository.connectVertex(143, 131, 145, 124);
            vertexRepository.connectVertex(145, 124, 163, 108);
            vertexRepository.connectVertex(163, 108, 173, 87);
            vertexRepository.connectVertex(173, 87, 185, 74);
            vertexRepository.connectVertex(185, 74, 192, 53);
            vertexRepository.connectVertex(192, 53, 187, 46);
            vertexRepository.connectVertex(187, 46, 170, 32);
            vertexRepository.connectVertex(170, 32, 148, 19);
            vertexRepository.connectVertex(148, 19, 128, 12);
            vertexRepository.connectVertex(128, 12, 118, 3); //end

            vertexRepository.connectVertex(103, 232, 107, 239); //to military base
            vertexRepository.connectVertex(107, 239, 108, 249);
            vertexRepository.connectVertex(108, 249, 113, 256);
            vertexRepository.connectVertex(113, 256, 185, 343); //junction 2

            vertexRepository.connectVertex(185, 343, 194, 327); //junction 2 right
            vertexRepository.connectVertex(194, 327, 199, 318);
            vertexRepository.connectVertex(199, 318, 218, 309);
            vertexRepository.connectVertex(218, 309, 233, 298);
            vertexRepository.connectVertex(233, 298, 243, 295);
            vertexRepository.connectVertex(243, 295, 274, 300);
            vertexRepository.connectVertex(274, 300, 296, 301);
            vertexRepository.connectVertex(296, 301, 308, 294);
            vertexRepository.connectVertex(308, 294, 324, 295);
            vertexRepository.connectVertex(324, 295, 328, 299);
            vertexRepository.connectVertex(328, 299, 329, 309);
            vertexRepository.connectVertex(329, 309, 334, 314);
            vertexRepository.connectVertex(334, 314, 338, 316);
            vertexRepository.connectVertex(338, 316, 373, 307);
            vertexRepository.connectVertex(373, 307, 378, 297);
            vertexRepository.connectVertex(378, 297, 384, 293);
            vertexRepository.connectVertex(384, 293, 395, 297);
            vertexRepository.connectVertex(395, 297, 400, 304);
            vertexRepository.connectVertex(400, 304, 402, 311);
            vertexRepository.connectVertex(402, 311, 408, 314);
            vertexRepository.connectVertex(408, 314, 421, 328);
            vertexRepository.connectVertex(421, 328, 423, 336);
            vertexRepository.connectVertex(423, 336, 426, 338);
            vertexRepository.connectVertex(426, 338, 434, 339);
            vertexRepository.connectVertex(434, 339, 439, 336);
            vertexRepository.connectVertex(439, 336, 447, 336); //end

            vertexRepository.connectVertex(185, 343, 199, 368); //junction 2 down
            vertexRepository.connectVertex(199, 368, 202, 386); //junction 3

            vertexRepository.connectVertex(202, 386, 204, 440); //junction 3 down + junction 4

            vertexRepository.connectVertex(202, 386, 197, 387); //junction 3 left
            vertexRepository.connectVertex(197, 387, 190, 397);
            vertexRepository.connectVertex(190, 397, 179, 407);
            vertexRepository.connectVertex(179, 407, 166, 415);
            vertexRepository.connectVertex(166, 415, 154, 421);
            vertexRepository.connectVertex(154, 421, 140, 429);
            vertexRepository.connectVertex(140, 429, 128, 447);
            vertexRepository.connectVertex(128, 447, 127, 456);
            vertexRepository.connectVertex(127, 456, 130, 461);
            vertexRepository.connectVertex(130, 461, 151, 483);
            vertexRepository.connectVertex(151, 483, 171, 505);
            vertexRepository.connectVertex(171, 505, 180, 514);
            vertexRepository.connectVertex(180, 514, 187, 515);
            vertexRepository.connectVertex(187, 515, 214, 508);
            vertexRepository.connectVertex(214, 508, 222, 511);
            vertexRepository.connectVertex(222, 511, 236, 519);
            vertexRepository.connectVertex(236, 519, 247, 521);
            vertexRepository.connectVertex(247, 521, 292, 530);
            vertexRepository.connectVertex(292, 530, 308, 535);
            vertexRepository.connectVertex(308, 535, 322, 531);
            vertexRepository.connectVertex(322, 531, 358, 513);
            vertexRepository.connectVertex(358, 513, 378, 504);
            vertexRepository.connectVertex(378, 504, 395, 507);
            vertexRepository.connectVertex(395, 507, 412, 503);
            vertexRepository.connectVertex(412, 503, 441, 491);
            vertexRepository.connectVertex(441, 491, 451, 482);
            vertexRepository.connectVertex(451, 482, 464, 477);
            vertexRepository.connectVertex(464, 477, 480, 482);
            vertexRepository.connectVertex(480, 482, 496, 487);
            vertexRepository.connectVertex(496, 487, 514, 488);
            vertexRepository.connectVertex(514, 488, 532, 482);
            vertexRepository.connectVertex(532, 482, 541, 474);
            vertexRepository.connectVertex(541, 474, 550, 458);
            vertexRepository.connectVertex(550, 458, 559, 449);
            vertexRepository.connectVertex(559, 449, 583, 424);
            vertexRepository.connectVertex(583, 424, 585, 418);
            vertexRepository.connectVertex(585, 418, 582, 403);
            vertexRepository.connectVertex(582, 403, 573, 385);
            vertexRepository.connectVertex(573, 385, 566, 368);
            vertexRepository.connectVertex(566, 368, 562, 362);
            vertexRepository.connectVertex(562, 362, 560, 350);
            vertexRepository.connectVertex(560, 350, 558, 343);
            vertexRepository.connectVertex(558, 343, 447, 330);
            vertexRepository.connectVertex(447, 330, 547, 325);
            vertexRepository.connectVertex(547, 325, 537, 336);
            vertexRepository.connectVertex(537, 336, 521, 340);
            vertexRepository.connectVertex(521, 340, 510, 344);
            vertexRepository.connectVertex(510, 344, 484, 344); // junction 5

            vertexRepository.connectVertex(204, 440, 209, 438); //junction 4 right
            vertexRepository.connectVertex(209, 438, 238, 415);
            vertexRepository.connectVertex(238, 415, 245, 411);
            vertexRepository.connectVertex(245, 411, 249, 407);
            vertexRepository.connectVertex(249, 407, 261, 399);
            vertexRepository.connectVertex(261, 399, 263, 394);
            vertexRepository.connectVertex(263, 394, 270, 390);
            vertexRepository.connectVertex(270, 390, 279, 388);
            vertexRepository.connectVertex(279, 388, 297, 372);
            vertexRepository.connectVertex(297, 372, 302, 372);
            vertexRepository.connectVertex(302, 372, 339, 387);
            vertexRepository.connectVertex(339, 387, 357, 400);
            vertexRepository.connectVertex(357, 400, 366, 409);
            vertexRepository.connectVertex(366, 409, 383, 420);
            vertexRepository.connectVertex(383, 420, 390, 421);
            vertexRepository.connectVertex(390, 421, 410, 400);
            vertexRepository.connectVertex(410, 400, 455, 353);
            vertexRepository.connectVertex(455, 353, 469, 343);
            vertexRepository.connectVertex(469, 343, 484,343); //junction 5

            vertexRepository.connectVertex(204, 440, 204, 470); //junction 4 down
            vertexRepository.connectVertex(204, 470, 208, 476);
            vertexRepository.connectVertex(208, 476, 412, 476);
            vertexRepository.connectVertex(412, 476, 435, 472);
            vertexRepository.connectVertex(435, 472, 468, 464);
            vertexRepository.connectVertex(468, 464, 488, 475);
            vertexRepository.connectVertex(488, 475, 495, 472);
            vertexRepository.connectVertex(495, 472, 506, 458);
            vertexRepository.connectVertex(506, 458, 522, 418);
            vertexRepository.connectVertex(522, 418, 521, 402);
            vertexRepository.connectVertex(521, 402, 508, 370);
            vertexRepository.connectVertex(508, 370, 492, 358);
            vertexRepository.connectVertex(492, 358, 484, 343); //junction 5

            vertexRepository.connectVertex(484, 343, 482, 318); //junction 5 up
            vertexRepository.connectVertex(482, 318, 483, 203);
            vertexRepository.connectVertex(483, 203, 491, 194);
            vertexRepository.connectVertex(491, 194, 500, 182); //junction 6

            vertexRepository.connectVertex(500, 182, 485, 175); //junction 6 left
            vertexRepository.connectVertex(485, 175, 478, 177);
            vertexRepository.connectVertex(478, 177, 459, 168);
            vertexRepository.connectVertex(459, 168, 453, 156);
            vertexRepository.connectVertex(453, 156, 449, 143);
            vertexRepository.connectVertex(449, 143, 438, 143);
            vertexRepository.connectVertex(438, 143, 430, 147);
            vertexRepository.connectVertex(430, 147, 422, 156);
            vertexRepository.connectVertex(422, 156, 407, 159);
            vertexRepository.connectVertex(407, 159, 397, 156);
            vertexRepository.connectVertex(397, 156, 393, 143);
            vertexRepository.connectVertex(393, 143, 379, 134);
            vertexRepository.connectVertex(379, 134, 375, 128);
            vertexRepository.connectVertex(375, 128, 364, 120);
            vertexRepository.connectVertex(364, 120, 354, 126);
            vertexRepository.connectVertex(354, 126, 346, 127);
            vertexRepository.connectVertex(346, 127, 318, 122);
            vertexRepository.connectVertex(318, 122, 314, 124);
            vertexRepository.connectVertex(314, 124, 292, 124);
            vertexRepository.connectVertex(292, 124, 280, 123);
            vertexRepository.connectVertex(280, 123, 268, 127);
            vertexRepository.connectVertex(268, 127, 257, 128);
            vertexRepository.connectVertex(257, 128, 242, 137);
            vertexRepository.connectVertex(242, 137, 218, 135);
            vertexRepository.connectVertex(218, 135, 201, 128);
            vertexRepository.connectVertex(201, 128, 186, 133);
            vertexRepository.connectVertex(186, 133, 170, 134); //end

            vertexRepository.connectVertex(500, 182, 508, 173); //junction 6 up
            vertexRepository.connectVertex(508, 173, 516, 167);
            vertexRepository.connectVertex(516, 167, 536, 134); //junction 7

            vertexRepository.connectVertex(536, 134, 537, 123); //junction 7 up
            vertexRepository.connectVertex(537, 123, 532, 111);
            vertexRepository.connectVertex(532, 111, 502, 41);
            vertexRepository.connectVertex(502, 41, 489, 17);
            vertexRepository.connectVertex(489, 17, 489, 1); // end

            vertexRepository.connectVertex(536, 134, 552, 140); //junction 7 right
            vertexRepository.connectVertex(552, 140, 558, 145);
            vertexRepository.connectVertex(558, 145, 580, 152);
            vertexRepository.connectVertex(580, 152, 592, 154);
            vertexRepository.connectVertex(592, 154, 603, 159);
            vertexRepository.connectVertex(603, 159, 611, 159);
            vertexRepository.connectVertex(611, 159, 621, 154); //junction 8

            vertexRepository.connectVertex(621, 154, 644, 158); //junction 8 right
            vertexRepository.connectVertex(644, 158, 648, 168);
            vertexRepository.connectVertex(648, 168, 674, 195); //end

            vertexRepository.connectVertex(621, 154, 615, 147); //junction 8 up
            vertexRepository.connectVertex(615, 147, 615, 137);
            vertexRepository.connectVertex(615, 137, 619, 132);
            vertexRepository.connectVertex(619, 132, 622, 123);
            vertexRepository.connectVertex(622, 123, 623, 118);
            vertexRepository.connectVertex(623, 118, 629, 116);
            vertexRepository.connectVertex(629, 116, 636, 121);
            vertexRepository.connectVertex(636, 121, 642, 122);
            vertexRepository.connectVertex(642, 122, 649, 128);
            vertexRepository.connectVertex(649, 128, 661, 125);
            vertexRepository.connectVertex(661, 125, 664, 119);
            vertexRepository.connectVertex(664, 119, 674, 110);

            Driver d1 = new Driver("Ali", 4, 67.0, 144.0);
            Driver d2 = new Driver("Bii", 4, 136.0, 106.0);
            // Customer c1 = new Customer("Abu", LocalTime.now(), 4, 10.0, 10.0, 16.0,
            // 10.0);
            // Customer c2 = new Customer("Jack", LocalTime.now(), 4, 50.0, 32.0, 10.0,
            // 30.0);
            // Destination dest1 = new Destination(c1.getDest_longitude(),
            // c1.getDest_latitude());
            // Destination dest2 = new Destination(c2.getDest_longitude(),
            // c2.getDest_latitude());

            driverRepository.save(d1);
            driverRepository.save(d2);
            // customerRepository.save(c1);
            // customerRepository.save(c2);
            // destinationRepository.save(dest1);
            // destinationRepository.save(dest2);
            // C1 and C2 choose destination
            // customerRepository.updateDestination(c1.getId(), dest1.getId());
            // customerRepository.updateDestination(c2.getId(), dest2.getId());
            // D1 Fetch C1
            // destinationRepository.connectToClosestVertex(dest1.getId(),
            // vertexRepository.findClosestNodes(dest1.getLongitude(),
            // dest1.getLatitude()).getId());
            driverRepository.connectToClosestVertex(d1.getId(),
                    vertexRepository.findClosestNodes(d1.getLongitude(),
                            d1.getLatitude()).getId());
            // customerRepository.connectToClosestVertex(c1.getId(),
            // vertexRepository
            // .findClosestNodes(c1.getLongitude(), c1.getLatitude())
            // .getId());
            // driverRepository.fetchCustomer(d1.getId(), c1.getId());
            // driverRepository.updateDriverStatus(d1.getId(), Status.NOT_AVAILABLE);
            // customerRepository.updateCustomerStatus(c1.getId(), Status.PENDING);
            // D2 Fetch C2
            // destinationRepository.connectToClosestVertex(dest2.getId(),
            // vertexRepository.findClosestNodes(dest2.getLongitude(),
            // dest2.getLatitude()).getId());
            driverRepository.connectToClosestVertex(d2.getId(),
                    vertexRepository.findClosestNodes(d2.getLongitude(),
                            d2.getLatitude()).getId());
            // customerRepository.connectToClosestVertex(c2.getId(),
            // vertexRepository
            // .findClosestNodes(c2.getLongitude(), c2.getLatitude())
            // .getId());
            // driverRepository.fetchCustomer(d2.getId(), c2.getId());
            // driverRepository.updateDriverStatus(d2.getId(), Status.NOT_AVAILABLE);
            // customerRepository.updateCustomerStatus(d2.getId(), Status.PENDING);
            // updateService.findShortestPath(d1.getId(), c1.getId());
            // updateService.findShortestPath(d2.getId(), c2.getId());
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list of allowed origins
        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    // @Bean
    // CommandLineRunner demo(Neo4jClient neo4jClient, VertexRepository
    // vertexRepository,
    // DriverRepository driverRepository,
    // CustomerRepository customerRepository, DestinationRepository
    // destinationRepository,
    // UpdateService updateService) {
    // return args -> {
    // vertexRepository.deleteAll();
    // driverRepository.deleteAll();
    // customerRepository.deleteAll();
    // destinationRepository.deleteAll();
    // Vertex a = new Vertex(10, 5);
    // Vertex b = new Vertex(8, 15);
    // Vertex c = new Vertex(13, 25);
    // Vertex d = new Vertex(42, 34);

    // a.connectesWith(b);
    // a.connectesWith(c);
    // d.connectesWith(b);
    // c.connectesWith(b);

    // Driver d1 = new Driver("Ali", 4, 23.0, 10.0);
    // Driver d2 = new Driver("Bii", 4, 30.0, 15.0);
    // Customer c1 = new Customer("Abu", LocalDateTime.now(), 4, 10.0, 10.0, 16.0,
    // 10.0);
    // Customer c2 = new Customer("Jack", LocalDateTime.now(), 4, 50.0, 32.0, 10.0,
    // 30.0);
    // Destination dest1 = new Destination(c1.getDest_longitude(),
    // c1.getDest_latitude());
    // Destination dest2 = new Destination(c2.getDest_longitude(),
    // c2.getDest_latitude());

    // vertexRepository.save(a);
    // vertexRepository.save(b);
    // vertexRepository.save(c);
    // vertexRepository.save(d);

    // driverRepository.save(d1);
    // driverRepository.save(d2);
    // customerRepository.save(c1);
    // customerRepository.save(c2);
    // destinationRepository.save(dest1);
    // destinationRepository.save(dest2);

    // // C1 and C2 choose destination
    // customerRepository.updateDestination(c1.getId(), dest1.getId());
    // customerRepository.updateDestination(c2.getId(), dest2.getId());

    // // D1 Fetch C1
    // destinationRepository.connectToClosestVertex(dest1.getId(),
    // vertexRepository.findClosestNodes(dest1.getLongitude(),
    // dest1.getLatitude()).getId());

    // driverRepository.connectToClosestVertex(d1.getId(),
    // vertexRepository.findClosestNodes(d1.getLongitude(),
    // d1.getLatitude()).getId());

    // customerRepository.connectToClosestVertex(c1.getId(),
    // vertexRepository
    // .findClosestNodes(c1.getLongitude(), c1.getLatitude())
    // .getId());

    // driverRepository.fetchCustomer(d1.getId(), c1.getId());
    // driverRepository.updateDriverStatus(d1.getId(), Status.NOT_AVAILABLE);
    // customerRepository.updateCustomerStatus(c1.getId(), Status.PENDING);

    // // D2 Fetch C2
    // destinationRepository.connectToClosestVertex(dest2.getId(),
    // vertexRepository.findClosestNodes(dest2.getLongitude(),
    // dest2.getLatitude()).getId());

    // driverRepository.connectToClosestVertex(d2.getId(),
    // vertexRepository.findClosestNodes(d2.getLongitude(),
    // d2.getLatitude()).getId());

    // customerRepository.connectToClosestVertex(c2.getId(),
    // vertexRepository
    // .findClosestNodes(c2.getLongitude(), c2.getLatitude())
    // .getId());

    // driverRepository.fetchCustomer(d2.getId(), c2.getId());
    // driverRepository.updateDriverStatus(d2.getId(), Status.NOT_AVAILABLE);
    // customerRepository.updateCustomerStatus(d2.getId(), Status.PENDING);

    // updateService.findShortestPath(d1.getId(), c1.getId());
    // updateService.findShortestPath(d2.getId(), c2.getId());
    // };
    // }
}
