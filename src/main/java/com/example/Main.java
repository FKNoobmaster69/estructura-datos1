package com.example;

import java.util.*;

public class Main {

    //Este es el juego del maraton en java, lo hice de un solo jugador vs la ignorancia las reglas son las mismas que en el juego de mesa original el usuario avanza 2km y si se equivoca retrocede 1 y su nivel de ignorancia aumenta 1, en vez de un dado ya mejor use random
    //Use estructura de datos ("Arreglos", "Colas", "Arraylist", "lista", "hashet").
    //El banco de preguntas son 100 por cada tema, aquí si usé IA

    //Variables inicializadas
    //"Hashet" para guardar las preguntas que ya han sido relizadas
    static HashSet<String> preguntasRealizadas = new HashSet<>();
    //colas aquí está el banco de preguntas
    static Queue<String[]>[] questionThemes = new LinkedList[6];
    static int distanciaJugador = 0;
    static int nivelIgnorancia = 0;
    static final int Distancia_Meta = 42;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        inicializarPreguntas();

        System.out.println("¡Bienvenido al juego de Maratón!");
        while (!juegoTerminado()) {
            mostrarProgreso();

            Integer temaRandom = getTemaRandom();
            if (temaRandom == null) {
                System.out.println("No quedan preguntas. ¡Juego terminado!");
                break;
            }

            System.out.println("El tema seleccionado es: " + getNombreTema(temaRandom));
            boolean preguntaRespondida = mostrarPregunta(temaRandom, scanner);
            if (!preguntaRespondida) {
                System.out.println("No hay más preguntas en este tema.");
            }
        }

        System.out.println("¡Juego terminado! Alcanzaste la meta con un nivel de ignorancia de " + nivelIgnorancia);
        scanner.close();
    }

    public static void inicializarPreguntas() {
        // inicializar las colas para cada tema
        for (int i = 0; i < questionThemes.length; i++) {
            questionThemes[i] = new LinkedList<>();
        }

        //"Arreglo" las preguntas y repuestas se almacenan en un conjunto de cadenas y se accede mediante índices
        // México
        questionThemes[0].add(new String[]{"¿Cuál es la capital de México?", "Ciudad de México", "Guadalajara", "Monterrey", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el principal idioma hablado en México?", "Español", "Náhuatl", "Inglés", "*1"});
        questionThemes[0].add(new String[]{"¿Qué civilización construyó Chichén Itzá?", "Maya", "Azteca", "Olmeca", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año ocurrió la Independencia de México?", "1810", "1821", "1789", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el líder del ejército insurgente en la Independencia?", "Miguel Hidalgo", "Emiliano Zapata", "Benito Juárez", "*1"});
        questionThemes[0].add(new String[]{"¿Qué estado de México tiene la mayor superficie?", "Chihuahua", "Sonora", "Durango", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el plato típico de la Ciudad de México?", "Tacos", "Pozole", "Enchiladas", "*1"});
        questionThemes[0].add(new String[]{"¿Qué gran civilización precolombina habitó el Valle de México?", "Azteca", "Maya", "Olmeca", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente de México implementó el Tratado de Libre Comercio de América del Norte?", "Carlos Salinas de Gortari", "Luis Donaldo Colosio", "Manuel Ávila Camacho", "*1"});
        questionThemes[0].add(new String[]{"¿Qué nombre recibe el museo más importante de México?", "Museo Nacional de Antropología", "Museo de Arte Moderno", "Museo Frida Kahlo", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el último emperador azteca?", "Moctezuma II", "Cuauhtémoc", "Huitzilopochtli", "*1"});
        questionThemes[0].add(new String[]{"¿Qué nación invadió México durante la guerra de independencia?", "España", "Francia", "Estados Unidos", "*1"});
        questionThemes[0].add(new String[]{"¿En qué fecha se celebra el Día de la Independencia de México?", "16 de septiembre", "20 de noviembre", "2 de noviembre", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el presidente de México durante la Revolución Mexicana?", "Francisco I. Madero", "Porfirio Díaz", "Emiliano Zapata", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente mexicano implementó la expropiación petrolera?", "Lázaro Cárdenas", "Benito Juárez", "Carlos Salinas de Gortari", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del famoso muralista mexicano conocido por su obra en el Palacio de Bellas Artes?", "Diego Rivera", "David Alfaro Siqueiros", "José Clemente Orozco", "*1"});
        questionThemes[0].add(new String[]{"¿Qué ciudad fue la sede del Imperio Mexica?", "Tenochtitlan", "Teotihuacán", "Puebla", "*1"});
        questionThemes[0].add(new String[]{"¿Qué antiguo presidente mexicano fue conocido como 'El Águila de la Revolución'?", "Venustiano Carranza", "Francisco Villa", "Lázaro Cárdenas", "*1"});
        questionThemes[0].add(new String[]{"¿En qué estado de México se encuentra el sitio arqueológico de Teotihuacán?", "Estado de México", "Puebla", "Chihuahua", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente mexicano firmó el Tratado de Libre Comercio de América del Norte (TLCAN)?", "Carlos Salinas de Gortari", "Vicente Fox", "Andrés Manuel López Obrador", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente de México promulgó la reforma educativa en 2013?", "Enrique Peña Nieto", "Vicente Fox", "Andrés Manuel López Obrador", "*1"});
        questionThemes[0].add(new String[]{"¿Qué organización indígena luchó contra la invasión española en México?", "Los mexicas", "Los mayas", "Los tarascos", "*1"});
        questionThemes[0].add(new String[]{"¿En qué ciudad se encuentra el Palacio de Bellas Artes?", "Ciudad de México", "Guadalajara", "Monterrey", "*1"});
        questionThemes[0].add(new String[]{"¿Qué evento ocurrió en 1910 en México?", "La Revolución Mexicana", "La Independencia de México", "La Reforma", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año se fundó la Ciudad de México?", "1325", "1521", "1810", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el primer emperador de México?", "Agustín de Iturbide", "Antonio López de Santa Anna", "Benito Juárez", "*1"});
        questionThemes[0].add(new String[]{"¿Qué planta es símbolo nacional de México?", "El nopal", "La flor de cempasúchil", "El águila", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre de la moneda de México?", "Peso mexicano", "Dólar", "Euro", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente de México implementó la reforma agraria?", "Lázaro Cárdenas", "Emilio Portes Gil", "Carlos Salinas de Gortari", "*1"});
        questionThemes[0].add(new String[]{"¿En qué guerra participó México entre 1846 y 1848?", "La Guerra México-Estados Unidos", "La Revolución Mexicana", "La Guerra de Independencia", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente de México implementó la política de la Revolución Mexicana?", "Venustiano Carranza", "Lázaro Cárdenas", "Álvaro Obregón", "*1"});
        questionThemes[0].add(new String[]{"¿Qué ciudad fue la capital del Imperio Azteca?", "Tenochtitlán", "Chichen Itzá", "Teotihuacán", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el primer presidente de la República Mexicana?", "Guadalupe Victoria", "Vicente Guerrero", "Benito Juárez", "*1"});
        questionThemes[0].add(new String[]{"¿Qué suceso histórico ocurrió el 2 de octubre de 1968 en México?", "La masacre de Tlatelolco", "La Revolución Mexicana", "La Independencia de México", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el líder del Ejército Trigarante?", "Agustín de Iturbide", "Miguel Hidalgo", "José María Morelos", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año se consumó la Independencia de México?", "1821", "1810", "1857", "*1"});
        questionThemes[0].add(new String[]{"¿Qué evento histórico sucedió en 1847 durante la invasión estadounidense a México?", "La Batalla de Chapultepec", "La Batalla de Puebla", "La Guerra de Reforma", "*1"});
        questionThemes[0].add(new String[]{"¿En qué periodo histórico se firmó la Constitución Mexicana de 1917?", "Revolución Mexicana", "Independencia de México", "Porfiriato", "*1"});
        questionThemes[0].add(new String[]{"¿En qué región de México se encuentra la selva Lacandona?", "Chiapas", "Oaxaca", "Yucatán", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del presidente que nacionalizó la industria del petróleo en 1938?", "Lázaro Cárdenas", "Manuel Ávila Camacho", "Carlos Salinas de Gortari", "*1"});
        questionThemes[0].add(new String[]{"¿Qué importante avenida atraviesa la Ciudad de México de norte a sur?", "Paseo de la Reforma", "Avenida Insurgentes", "Avenida Juárez", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso pintor mexicano es conocido por sus murales en la Secretaría de Educacion Pública?", "Diego Rivera", "José Clemente Orozco", "David Alfaro Siqueiros", "*1"});
        questionThemes[0].add(new String[]{"¿Qué ciudad fue la sede de la primera capital del Imperio Mexica?", "Tenochtitlán", "Teotihuacán", "Texcoco", "*1"});
        questionThemes[0].add(new String[]{"¿Qué nombre recibió el imperio de los mexicas?", "Triple Alianza", "Confederación Azteca", "Imperio Mexicano", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famosa zona arqueológica está ubicada en el estado de Oaxaca?", "Monte Albán", "Teotihuacán", "Chichen Itzá", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el presidente mexicano durante la expropiación petrolera?", "Lázaro Cárdenas", "Adolfo López Mateos", "Miguel de la Madrid", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del famoso científico mexicano que desarrolló el chile habanero?", "Emilio Portes Gil", "Carlos Slim", "Luis Federico", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el líder del ejército republicano durante la invasión estadounidense a México?", "Antonio López de Santa Anna", "Benito Juárez", "Vicente Guerrero", "*1"});
        questionThemes[0].add(new String[]{"¿Qué isla mexicana fue famosa por su prisión durante el Porfiriato?", "Isla del Carmen", "Isla de las Mujeres", "Isla María Madre", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso escritor mexicano recibió el Premio Nobel de Literatura?", "Octavio Paz", "Carlos Fuentes", "Juan Rulfo", "*1"});
        questionThemes[0].add(new String[]{"¿Qué evento histórico es conocido como la 'Matanza de Tlatelolco'?", "La masacre estudiantil de 1968", "La Revolución Mexicana", "La Guerra Cristera", "*1"});
        questionThemes[0].add(new String[]{"¿En qué fecha se celebran las fiestas de 'Día de los Muertos' en México?", "1 y 2 de noviembre", "16 de septiembre", "24 de diciembre", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente mexicano implementó el programa 'Oportunidades'?", "Vicente Fox", "Carlos Salinas de Gortari", "Enrique Peña Nieto", "*1"});
        questionThemes[0].add(new String[]{"¿Qué ciudad mexicana fue la sede de la primera exposición universal?", "México", "Guadalajara", "Monterrey", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre de la pirámide que se encuentra en la ciudad de Teotihuacán?", "Pirámide del Sol", "Pirámide de la Luna", "Pirámide de Kukulkán", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el líder de los rebeldes durante la Guerra Cristera?", "José María Robles", "Emiliano Zapata", "Ciriaco Vázquez", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es la capital de Yucatán?", "Mérida", "Cancún", "Campeche", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año México se incorporó al Tratado de Libre Comercio de América del Norte (TLCAN)?", "1994", "2000", "1992", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente mexicano impulsó la creación del Instituto Mexicano del Seguro Social (IMSS)?", "Manuel Ávila Camacho", "Lázaro Cárdenas", "Miguel de la Madrid", "*1"});
        questionThemes[0].add(new String[]{"¿Qué lago es el más grande de México?", "Lago de Chapala", "Lago de Pátzcuaro", "Lago de Texcoco", "*1"});
        questionThemes[0].add(new String[]{"¿En qué ciudad se celebra la Feria Internacional del Libro?", "Guadalajara", "Ciudad de México", "Monterrey", "*1"});
        questionThemes[0].add(new String[]{"¿Qué civilización construyó la ciudad de Teotihuacán?", "Teotihuacanos", "Aztecas", "Olmecas", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es la bebida alcohólica tradicional de México?", "Tequila", "Ron", "Vino", "*1"});
        questionThemes[0].add(new String[]{"¿Qué volcán mexicano es el más alto?", "Pico de Orizaba", "Popocatépetl", "Nevado de Toluca", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año comenzó la Revolución Mexicana?", "1910", "1900", "1920", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el platillo típico del estado de Oaxaca?", "Mole", "Tacos al pastor", "Ceviche", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del principal puerto de México en el Pacífico?", "Manzanillo", "Acapulco", "Veracruz", "*1"});
        questionThemes[0].add(new String[]{"¿En qué estado de México se encuentra la Reserva de la Biosfera de la Mariposa Monarca?", "Michoacán", "Guerrero", "Chiapas", "*1"});
        questionThemes[0].add(new String[]{"¿Qué presidente mexicano nacionalizó la industria eléctrica en 1960?", "Adolfo López Mateos", "Lázaro Cárdenas", "Manuel Ávila Camacho", "*1"});
        questionThemes[0].add(new String[]{"¿Qué escritor mexicano ganó el Premio Nobel de Literatura en 1990?", "Octavio Paz", "Carlos Fuentes", "Juan Rulfo", "*1"});
        questionThemes[0].add(new String[]{"¿Qué país tiene la frontera más larga con México?", "Estados Unidos", "Guatemala", "Belice", "*1"});
        questionThemes[0].add(new String[]{"¿Quién fue el último presidente de México durante el Porfiriato?", "Porfirio Díaz", "Francisco I. Madero", "Emilio Portes Gil", "*1"});
        questionThemes[0].add(new String[]{"¿En qué ciudad se encuentra el Ángel de la Independencia?", "Ciudad de México", "Guadalajara", "Monterrey", "*1"});
        questionThemes[0].add(new String[]{"¿Qué nombre recibe la fiesta mexicana que celebra el 15 de septiembre?", "Fiesta de la Independencia", "Día de los Muertos", "Fiesta de la Revolución", "*1"});
        questionThemes[0].add(new String[]{"¿Qué estado mexicano es conocido por su producción de tequila?", "Jalisco", "Baja California", "Sonora", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año se fundó la Universidad Nacional Autónoma de México (UNAM)?", "1910", "1929", "1950", "*1"});
        questionThemes[0].add(new String[]{"¿Qué antigua ciudad maya se encuentra en el estado de Yucatán?", "Uxmal", "Chichen Itzá", "Tulum", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del principal río de México?", "Río Bravo", "Río Usumacinta", "Río Lerma", "*1"});
        questionThemes[0].add(new String[]{"¿En qué estado mexicano se encuentra el Parque Nacional de las Barrancas del Cobre?", "Chihuahua", "Durango", "Sinaloa", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el idioma oficial de México?", "Español", "Náhuatl", "Maya", "*1"});
        questionThemes[0].add(new String[]{"¿Qué evento histórico ocurrió en 1994 en México?", "El levantamiento del Ejército Zapatista de Liberación Nacional", "La Revolución Mexicana", "La expropiación petrolera", "*1"});
        questionThemes[0].add(new String[]{"¿Qué estado mexicano tiene la mayor población?", "Estado de México", "Jalisco", "Nuevo León", "*1"});
        questionThemes[0].add(new String[]{"¿Qué es la ‘Día de los Muertos’ en México?", "Una festividad para honrar a los muertos", "El día de la independencia", "Una fiesta religiosa", "*1"});
        questionThemes[0].add(new String[]{"¿Qué civilización prehispánica construyó la ciudad de Tenochtitlán?", "Azteca", "Olmeca", "Maya", "*1"});
        questionThemes[0].add(new String[]{"¿Qué región de México es conocida por sus cenotes?", "Yucatán", "Chiapas", "Guerrero", "*1"});
        questionThemes[0].add(new String[]{"¿Quién pintó el mural ‘El hombre controlador del universo’?", "Diego Rivera", "David Alfaro Siqueiros", "José Clemente Orozco", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso río fluye a través de la frontera entre México y Estados Unidos?", "Río Bravo", "Río Usumacinta", "Río Amazonas", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año fue promulgada la Constitución Mexicana?", "1917", "1824", "1857", "*1"});
        questionThemes[0].add(new String[]{"¿Qué estado de México es famoso por su producción de mezcal?", "Oaxaca", "Guerrero", "Sonora", "*1"});
        questionThemes[0].add(new String[]{"¿Qué civilización vivió en las actuales regiones de Veracruz y Tabasco?", "Olmeca", "Maya", "Azteca", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año terminó la Revolución Mexicana?", "1920", "1910", "1930", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el principal puerto de México en el Golfo de México?", "Veracruz", "Mazatlán", "Acapulco", "*1"});
        questionThemes[0].add(new String[]{"¿Qué antiguo presidente mexicano fue conocido como 'El Presidente Caballero'?", "Adolfo López Mateos", "Lázaro Cárdenas", "Vicente Fox", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso sitio arqueológico se encuentra en el estado de Chiapas?", "Palenque", "Teotihuacán", "Chichen Itzá", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famosa playa se encuentra en el estado de Guerrero?", "Acapulco", "Cozumel", "Cabo San Lucas", "*1"});
        questionThemes[0].add(new String[]{"¿Qué guerra se libró entre México y Estados Unidos entre 1846 y 1848?", "La Guerra México-Estados Unidos", "La Guerra de Reforma", "La Revolución Mexicana", "*1"});
        questionThemes[0].add(new String[]{"¿En qué año se celebró el centenario de la Revolución Mexicana?", "2010", "2000", "2020", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del desierto más extenso de México?", "Desierto de Chihuahua", "Desierto de Sonora", "Desierto de Baja California", "*1"});
        questionThemes[0].add(new String[]{"¿Qué península se encuentra al noroeste de México?", "Península de Baja California", "Península de Yucatán", "Península de Florida", "*1"});
        questionThemes[0].add(new String[]{"¿Qué importante puerto se ubica en el estado de Veracruz, en el Golfo de México?", "Veracruz", "Cancún", "Acapulco", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál de estos estados no comparte frontera con Estados Unidos?", "Chiapas", "Sonora", "Baja California", "*1"});
        questionThemes[0].add(new String[]{"¿Qué importante sitio arqueológico Maya se encuentra en el estado de Chiapas?", "Palenque", "Teotihuacán", "Monte Albán", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál de estos platillos es originario del estado de Puebla?", "Mole poblano", "Cochinita pibil", "Tacos al pastor", "*1"});
        questionThemes[0].add(new String[]{"¿Qué importante celebración se lleva a cabo en Tlacotalpan, Veracruz?", "Fiestas de la Candelaria", "Guelaguetza", "Día de Muertos", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso compositor mexicano escribió 'Bésame Mucho'?", "Consuelo Velázquez", "Agustín Lara", "José Alfredo Jiménez", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso luchador mexicano es conocido como 'El Santo'?", "Rodolfo Guzmán Huerta", "Blue Demon", "Mil Máscaras", "*1"});
        questionThemes[0].add(new String[]{"¿En qué ciudad se encuentra el famoso Acueducto de Querétaro?", "Querétaro", "Morelia", "Guanajuato", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál de estos cuerpos de agua no es un lago mexicano?", "Lago de Nicaragua", "Lago de Chapala", "Lago de Pátzcuaro", "*1"});
        questionThemes[0].add(new String[]{"¿Qué importante zona arqueológica se encuentra cerca de la ciudad de Oaxaca?", "Monte Albán", "Chichén Itzá", "Tulum", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál de estos estados no se encuentra en la península de Yucatán?", "Guerrero", "Yucatán", "Quintana Roo", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del mariachi más famoso de México?", "Mariachi Vargas de Tecalitlán", "Mariachi Los Camperos", "Mariachi Sol de México", "*1"});
        questionThemes[0].add(new String[]{"¿En qué península se encuentra el estado de Baja California Sur?", "Península de Baja California", "Península de Yucatán", "Península de Nicoya", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del baile folclórico más representativo de Jalisco?", "Jarabe Tapatío", "Danza de los Viejitos", "La Bamba", "*1"});
        questionThemes[0].add(new String[]{"¿Qué famoso volcán se encuentra cerca de la Ciudad de México?", "Popocatépetl", "Pico de Orizaba", "Nevado de Toluca", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del sitio arqueológico maya más importante de Chiapas?", "Palenque", "Bonampak", "Yaxchilán", "*1"});
        questionThemes[0].add(new String[]{"¿Qué producto agrícola exporta México en grandes cantidades?", "Aguacate", "Plátano", "Café", "*1"});
        questionThemes[0].add(new String[]{"¿Cuál es el nombre del cañón más grande de México?", "Barrancas del Cobre", "Cañón del Sumidero", "Cañón de Fernández", "*1"});
        questionThemes[0].add(new String[]{"¿Qué ciudad es famosa por su producción de plata?", "Taxco", "Guanajuato", "Zacatecas", "*1"});

        // Historia Universal
        questionThemes[1].add(new String[]{"¿Quién fue el primer emperador romano?", "Augusto", "Julio César", "Nerón", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año comenzó la Revolución Francesa?", "1789", "1776", "1799", "*1"});
        questionThemes[1].add(new String[]{"¿Quién escribió 'La Divina Comedia'?", "Dante Alighieri", "William Shakespeare", "Miguel de Cervantes", "*1"});
        questionThemes[1].add(new String[]{"¿Qué civilización construyó las pirámides de Giza?", "Antiguo Egipto", "Antigua Grecia", "Imperio Romano", "*1"});
        questionThemes[1].add(new String[]{"¿En qué siglo comenzó la Edad Media?", "Siglo V", "Siglo X", "Siglo XV", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la Unión Soviética durante la mayor parte de la Guerra Fría?", "Nikita Jrushchov", "Iósif Stalin", "Mijaíl Gorbachov", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Revolución Rusa?", "1917", "1905", "1922", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento de independencia de la India?", "Mahatma Gandhi", "Jawaharlal Nehru", "Sardar Vallabhbhai Patel", "*1"});
        questionThemes[1].add(new String[]{"¿Qué país fue dividido por el Muro de Berlín?", "Alemania", "Austria", "Polonia", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se firmó la Carta de las Naciones Unidas?", "1945", "1919", "1949", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el primer presidente de Sudáfrica después del apartheid?", "Nelson Mandela", "Desmond Tutu", "F. W. de Klerk", "*1"});
        questionThemes[1].add(new String[]{"¿Qué imperio se extendió por gran parte de América del Sur antes de la llegada de los españoles?", "Imperio Inca", "Imperio Azteca", "Imperio Maya", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año comenzó la Guerra de Corea?", "1950", "1945", "1953", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal exponente del Renacimiento italiano?", "Leonardo da Vinci", "Miguel Ángel", "Rafael Sanzio", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento marcó el inicio de la era de los descubrimientos?", "Viajes de Cristóbal Colón", "La caída de Constantinopla", "La invención de la imprenta", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal líder de la Reforma Protestante?", "Martín Lutero", "Juan Calvino", "Enrique VIII", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se proclamó la independencia de las Trece Colonias?", "1776", "1783", "1789", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico enfrentó a Estados Unidos y la Unión Soviética sin llegar a una guerra directa?", "Guerra Fría", "Guerra de Vietnam", "Guerra de Corea", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el faraón que unificó el Alto y Bajo Egipto?", "Menes (Narmer)", "Tutankamón", "Ramsés II", "*1"});
        questionThemes[1].add(new String[]{"¿Qué cultura mesoamericana creó un complejo sistema de escritura jeroglífica?", "Maya", "Azteca", "Olmeca", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el fundador del Imperio Persa?", "Ciro el Grande", "Darío I", "Jerjes I", "*1"});
        questionThemes[1].add(new String[]{"¿En qué siglo se desarrolló el Imperio Romano de Oriente (Bizantino)?", "Siglo IV al XV", "Siglo I al V", "Siglo X al XIII", "*1"});
        questionThemes[1].add(new String[]{"¿Qué cultura prehispánica construyó la ciudad de Teotihuacan?", "Teotihuacanos", "Aztecas", "Olmecas", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el conquistador español del Imperio Azteca?", "Hernán Cortés", "Francisco Pizarro", "Vasco Núñez de Balboa", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Revolución Gloriosa en Inglaterra?", "1688", "1642", "1776", "*1"});
        questionThemes[1].add(new String[]{"¿Qué ideología política promovió la expansión de la Unión Soviética durante el siglo XX?", "Comunismo", "Capitalismo", "Fascismo", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento se considera el inicio de la Edad Contemporánea?", "Revolución Francesa", "Revolución Industrial", "Primera Guerra Mundial", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento de derechos civiles en Estados Unidos en la década de 1960?", "Martin Luther King Jr.", "Malcolm X", "Rosa Parks", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico se libró entre Estados Unidos y Vietnam?", "Guerra de Vietnam", "Guerra de Corea", "Guerra Fría", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el primer hombre en pisar la Luna?", "Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se reunieron los países en la Conferencia de Bandung?", "1955", "1945", "1961", "*1"});
        questionThemes[1].add(new String[]{"¿Qué sistema político se caracterizó por la división de Alemania tras la Segunda Guerra Mundial?", "Guerra Fría", "Telón de Acero", "Pacto de Varsovia", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal exponente del movimiento ilustrado francés?", "Voltaire", "Montesquieu", "Rousseau", "*1"});
        questionThemes[1].add(new String[]{"¿En qué ciudad se firmó el tratado que puso fin a la Segunda Guerra Mundial con Japón?", "San Francisco", "Yalta", "Potsdam", "*1"});
        questionThemes[1].add(new String[]{"¿Qué país fue gobernado por un régimen fascista liderado por Benito Mussolini?", "Italia", "Alemania", "España", "*1"});
        questionThemes[1].add(new String[]{"¿Qué faraona gobernó Egipto durante el Reino Nuevo?", "Hatshepsut", "Cleopatra", "Nefertiti", "*1"});
        questionThemes[1].add(new String[]{"¿Qué antigua civilización floreció en el valle del Indo?", "Cultura del Valle del Indo", "Mesopotamia", "Antiguo Egipto", "*1"});
        questionThemes[1].add(new String[]{"¿Quién lideró la expedición que circunnavegó por primera vez el globo terráqueo?", "Fernando de Magallanes", "Cristóbal Colón", "Vasco da Gama", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se firmó la Declaración Universal de los Derechos Humanos?", "1948", "1945", "1950", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento histórico se conoce como la 'Noche de los Cristales Rotos'?", "Ataques contra judíos en Alemania nazi", "La caída del Muro de Berlín", "El inicio de la Segunda Guerra Mundial", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el autor de 'El contrato social'?", "Jean-Jacques Rousseau", "John Locke", "Thomas Hobbes", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico se desarrolló entre 1914 y 1918?", "Primera Guerra Mundial", "Guerra de Secesión", "Guerra Fría", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año comenzó la Guerra Civil Española?", "1936", "1939", "1931", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la Alemania nazi?", "Adolf Hitler", "Heinrich Himmler", "Joseph Goebbels", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento marcó el inicio de la Segunda Guerra Mundial en Europa?", "Invasión de Polonia", "Anexión de Austria", "Conferencia de Múnich", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el emperador de Francia durante la Revolución Francesa y el periodo napoleónico?", "Napoleón Bonaparte", "Luis XVI", "Maximilien Robespierre", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la caída del Imperio Romano de Occidente?", "476 d.C.", "395 d.C.", "1453 d.C.", "*1"});
        questionThemes[1].add(new String[]{"¿Qué cultura precolombina construyó Machu Picchu?", "Imperio Inca", "Imperio Azteca", "Cultura Maya", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal líder de la Revolución Cubana?", "Fidel Castro", "Ernesto 'Che' Guevara", "Camilo Cienfuegos", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Revolución China?", "1949", "1911", "1927", "*1"});
        questionThemes[1].add(new String[]{"¿Qué ideología política promovía la igualdad social y la abolición de la propiedad privada?", "Comunismo", "Capitalismo", "Fascismo", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el primer ministro británico durante la Segunda Guerra Mundial?", "Winston Churchill", "Neville Chamberlain", "Clement Attlee", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo el ataque a Pearl Harbor?", "1941", "1939", "1945", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento histórico se conoce como la 'Gran Depresión'?", "Crisis económica mundial en la década de 1930", "Crisis del petróleo en la década de 1970", "Crisis financiera de 2008", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento sufragista en el Reino Unido?", "Emmeline Pankhurst", "Millicent Fawcett", "Virginia Woolf", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la unificación de Alemania?", "1990", "1949", "1961", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico enfrentó a Estados Unidos e Irak en 2003?", "Guerra de Irak", "Guerra del Golfo", "Guerra Irán-Irak", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal artífice de la independencia de México?", "Miguel Hidalgo y Costilla", "José María Morelos", "Vicente Guerrero", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se firmó el Tratado de Versalles que puso fin a la Primera Guerra Mundial?", "1919", "1918", "1920", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento se considera el inicio de la Guerra Fría?", "Bloqueo de Berlín", "Conferencia de Yalta", "Prueba de la bomba atómica por la URSS", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento por los derechos civiles en Sudáfrica contra el Apartheid?", "Nelson Mandela", "Desmond Tutu", "Steve Biko", "*1"});
        questionThemes[1].add(new String[]{"¿Qué país fue la cuna del Renacimiento?", "Italia", "Francia", "Inglaterra", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Revolución de Octubre en Rusia?", "1917", "1905", "1922", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el último zar de Rusia?", "Nicolás II", "Alejandro III", "Pedro I", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento desencadenó la entrada de Estados Unidos en la Segunda Guerra Mundial?", "Ataque a Pearl Harbor", "Invasión de Polonia", "Batalla de Inglaterra", "*1"});
        questionThemes[1].add(new String[]{"¿Qué civilización antigua se desarrolló en la región de Mesopotamia entre los ríos Tigris y Éufrates?", "Mesopotamia", "Antiguo Egipto", "Civilización del Valle del Indo", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el autor de '1984'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la caída del Imperio Bizantino a manos de los otomanos?", "1453", "1204", "1066", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto se libró entre Corea del Norte y Corea del Sur entre 1950 y 1953?", "Guerra de Corea", "Guerra de Vietnam", "Guerra Fría", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la Revolución Islámica en Irán?", "Ayatolá Jomeini", "Mohammad Reza Pahlevi", "Mahmud Ahmadineyad", "*1"});
        questionThemes[1].add(new String[]{"¿Qué imperio precolombino dominó gran parte del territorio que hoy es México?", "Imperio Azteca (Mexica)", "Imperio Maya", "Imperio Inca", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la primera circunnavegación del planeta Tierra?", "1519-1522", "1492", "1600", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el autor de 'Don Quijote de la Mancha'?", "Miguel de Cervantes", "William Shakespeare", "Lope de Vega", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento se conoce como la 'Revolución de Terciopelo'?", "Caída del comunismo en Checoslovaquia", "Caída del Muro de Berlín", "Revolución Húngara de 1956", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la expedición que conquistó el Imperio Inca?", "Francisco Pizarro", "Hernán Cortés", "Diego de Almagro", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Batalla de Hastings?", "1066", "1453", "1789", "*1"});
        questionThemes[1].add(new String[]{"¿Qué periodo histórico se caracteriza por el desarrollo de la máquina de vapor y la industrialización?", "Revolución Industrial", "Revolución Francesa", "Renacimiento", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento pacifista en la India conocido como Satyagraha?", "Mahatma Gandhi", "Jawaharlal Nehru", "Indira Gandhi", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo el fin de la Unión Soviética?", "1991", "1989", "1985", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento histórico se conoce como la 'Conferencia de Wannsee'?", "Planificación del Holocausto", "Firma del Pacto de Varsovia", "División de Alemania", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el principal exponente del existencialismo?", "Jean-Paul Sartre", "Albert Camus", "Simone de Beauvoir", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico enfrentó a Estados Unidos y la Unión Soviética de forma indirecta a través de terceros países?", "Guerra Fría", "Segunda Guerra Mundial", "Guerra de Corea", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Crisis de los Misiles de Cuba?", "1962", "1961", "1963", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la Alemania Oriental (República Democrática Alemana)?", "Erich Honecker", "Walter Ulbricht", "Willy Brandt", "*1"});
        questionThemes[1].add(new String[]{"¿Qué país fue el principal escenario de la Guerra de Vietnam?", "Vietnam", "Corea", "Laos", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el arquitecto de la unificación alemana?", "Helmut Kohl", "Willy Brandt", "Konrad Adenauer", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se fundó la Organización de las Naciones Unidas (ONU)?", "1945", "1919", "1948", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento se conoce como la 'Primavera de Praga'?", "Intento de liberalización en Checoslovaquia", "Revolución Húngara", "Protestas en Polonia", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el primer presidente de la República de China (Taiwán)?", "Chiang Kai-shek", "Mao Zedong", "Sun Yat-sen", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto bélico se libró en la península de Corea entre 1950 y 1953?", "Guerra de Corea", "Guerra de Vietnam", "Guerra Sino-Japonesa", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder de la Revolución Cultural en China?", "Mao Zedong", "Deng Xiaoping", "Zhou Enlai", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la caída de Saigón, marcando el fin de la Guerra de Vietnam?", "1975", "1973", "1979", "*1"});
        questionThemes[1].add(new String[]{"¿Qué país africano fue escenario de un genocidio en 1994 entre hutus y tutsis?", "Ruanda", "Sudáfrica", "Sudán", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento de independencia de Argelia?", "Ahmed Ben Bella", "Houari Boumédiène", "Chadli Bendjedid", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la partición de la India y la creación de Pakistán?", "1947", "1945", "1949", "*1"});
        questionThemes[1].add(new String[]{"¿Qué conflicto se libró entre Israel y varios países árabes en 1967?", "Guerra de los Seis Días", "Guerra de Yom Kipur", "Guerra del Sinaí", "*1"});
        questionThemes[1].add(new String[]{"¿Quién fue el líder del movimiento de independencia de Kenia?", "Jomo Kenyatta", "Tom Mboya", "Daniel arap Moi", "*1"});
        questionThemes[1].add(new String[]{"¿En qué año se produjo la Revolución de los Claveles en Portugal?", "1974", "1975", "1976", "*1"});
        questionThemes[1].add(new String[]{"¿Qué evento marcó el fin del dominio colonial español en la mayor parte de América Latina?", "Guerras de Independencia Hispanoamericanas", "La invasión napoleónica a España", "La Revolución Francesa", "*1"});

        // Geografía Universal
        questionThemes[2].add(new String[]{"¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Yangtsé", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el monte más alto del mundo?", "Everest", "K2", "Kangchenjunga", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente está el desierto del Sahara?", "África", "Asia", "América", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el país más grande del mundo por superficie terrestre?", "Rusia", "Canadá", "China", "*1"});
        questionThemes[2].add(new String[]{"¿Qué océano separa América de Europa?", "Atlántico", "Pacífico", "Índico", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentra Australia?", "Oceanía", "Asia", "Antártida", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el desierto más grande del mundo (no polar)?", "Sahara", "Gobi", "Arábigo", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la mayor cantidad de islas?", "Indonesia", "Filipinas", "Japón", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Australia?", "Canberra", "Sídney", "Melbourne", "*1"});
        questionThemes[2].add(new String[]{"¿Qué continente tiene la mayor población?", "Asia", "África", "Europa", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el país más pequeño del mundo por área?", "Ciudad del Vaticano", "Mónaco", "Nauru", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la mayor población del mundo?", "India", "China", "Estados Unidos", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Italia?", "Roma", "Milán", "Florencia", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentra el río Amazonas?", "América del Sur", "África", "Asia", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el continente más pequeño en área terrestre?", "Oceanía", "Europa", "Antártida", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país se extiende entre Europa y Asia?", "Rusia", "Turquía", "Kazajistán", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el punto más profundo del océano conocido como la Fosa de las Marianas se encuentra en el océano?", "Pacífico", "Atlántico", "Índico", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el lago de agua dulce más grande del mundo por volumen?", "Baikal", "Superior", "Victoria", "*1"});
        questionThemes[2].add(new String[]{"¿Qué cordillera separa Europa de Asia?", "Montes Urales", "Montes Pirineos", "Montes Alpes", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Japón?", "Tokio", "Kioto", "Osaka", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Canadá?", "Ottawa", "Toronto", "Montreal", "*1"});
        questionThemes[2].add(new String[]{"¿Qué río cruza el desierto del Sahara?", "Nilo", "Níger", "Congo", "*1"});
        questionThemes[2].add(new String[]{"¿Cuántos países conforman el Reino Unido?", "4", "3", "5", "*1"});
        questionThemes[2].add(new String[]{"¿Qué mar se encuentra entre África y Europa?", "Mediterráneo", "Rojo", "Negro", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido como la 'Tierra del Sol Naciente'?", "Japón", "China", "Corea del Sur", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el río más largo de Europa?", "Volga", "Danubio", "Rin", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el lago más profundo del mundo?", "Baikal", "Tanganyika", "Caspio", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la mayor cantidad de volcanes activos?", "Indonesia", "Japón", "Filipinas", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentra el Monte Kilimanjaro?", "África", "Asia", "Sudamérica", "*1"});
        questionThemes[2].add(new String[]{"¿Qué ciudad es conocida como la 'Venecia del Norte'?", "Ámsterdam", "San Petersburgo", "Brujas", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene el territorio más grande de Sudamérica?", "Brasil", "Argentina", "Colombia", "*1"});
        questionThemes[2].add(new String[]{"¿En qué océano se encuentra el Triángulo de las Bermudas?", "Atlántico", "Pacífico", "Índico", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la isla más grande del mundo?", "Groenlandia", "Nueva Guinea", "Borneo", "*1"});
        questionThemes[2].add(new String[]{"¿Qué mar está al norte de Turquía?", "Mar Negro", "Mar de Mármara", "Mar Egeo", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país está completamente rodeado por Sudáfrica?", "Lesoto", "Suazilandia (Esuatini)", "Botsuana", "*1"});
        questionThemes[2].add(new String[]{"¿Qué océano tiene la mayor profundidad media?", "Pacífico", "Atlántico", "Índico", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Argentina?", "Buenos Aires", "Córdoba", "Mendoza", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentran los Alpes?", "Europa", "Asia", "África", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la ciudad más poblada de África?", "Lagos", "El Cairo", "Kinshasa", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la mayor cantidad de husos horarios?", "Francia", "Rusia", "Estados Unidos", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido por su forma de bota?", "Italia", "Grecia", "Portugal", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el mar más salado del mundo?", "Mar Muerto", "Mar Rojo", "Mediterráneo", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el río con mayor caudal del mundo?", "Amazonas", "Congo", "Misisipi", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el Cañón del Colca?", "Perú", "Bolivia", "Chile", "*1"});
        questionThemes[2].add(new String[]{"¿Qué ciudad se encuentra en dos continentes?", "Estambul", "Moscú", "El Cairo", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el punto más septentrional del planeta?", "Polo Norte", "Groenlandia", "Svalbard", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el único continente sin desiertos extensos?", "Europa", "Antártida", "Norteamérica", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la capital situada a mayor altitud sobre el nivel del mar?", "Bolivia (La Paz)", "Ecuador (Quito)", "Perú (Cusco)", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país europeo es conocido como 'la tierra de los mil lagos'?", "Finlandia", "Suecia", "Noruega", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el archipiélago más grande del mundo por extensión?", "Indonesia", "Filipinas", "Maldivas", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente nace el río Ganges?", "Asia", "África", "Sudamérica", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país se asemeja a la forma de un dragón en su mapa?", "Vietnam", "Corea del Sur", "Bután", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país sudamericano no tiene costa marítima?", "Bolivia", "Paraguay", "Perú", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el volcán activo más alto del mundo (Ojos del Salado)?", "Chile", "Argentina", "Ecuador", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la isla habitada más remota del mundo?", "Tristán de Acuña", "Isla de Pascua", "Hawái", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país se encuentra entre China e India en el Himalaya?", "Nepal", "Bután", "Birmania (Myanmar)", "*1"});
        questionThemes[2].add(new String[]{"¿En qué océano se encuentra la isla de Madagascar?", "Índico", "Atlántico", "Pacífico", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el país con la mayor densidad de población del mundo (sin incluir microestados)?", "Bangladés", "Corea del Sur", "Japón", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el Monte Kailash, considerado sagrado en varias religiones?", "Tíbet (China)", "Nepal", "India", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se extiende principalmente el desierto de Gobi?", "Asia", "África", "Australia", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país europeo es famoso por sus fiordos?", "Noruega", "Islandia", "Escocia", "*1"});
        questionThemes[2].add(new String[]{"¿Qué ciudad se considera el centro geográfico de Europa?", "Vilnius (Lituania)", "Polotsk (Bielorrusia)", "Kremsmünster (Austria)", "*1"}); // Hay debate sobre la ubicación exacta
        questionThemes[2].add(new String[]{"¿Cuál es el estrecho que separa África de Europa?", "Estrecho de Gibraltar", "Estrecho de Bósforo", "Estrecho de Ormuz", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país alberga las Cataratas del Iguazú, compartidas con Argentina?", "Brasil", "Paraguay", "Uruguay", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la península más grande del mundo?", "Península arábiga", "Península indostánica", "Península escandinava", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene la mayor reserva de agua dulce del mundo (Lago Baikal)?", "Rusia", "Canadá", "Estados Unidos", "*1"});
        questionThemes[2].add(new String[]{"¿Qué isla volcánica es conocida por sus moáis?", "Isla de Pascua", "Hawái", "Islandia", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país conecta América del Norte y del Sur?", "Panamá", "Colombia", "México", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el punto más bajo de la Tierra firme?", "Costa del Mar Muerto", "Depresión de Turpan", "Valle de la Muerte", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país asiático es conocido como 'la tierra de la eterna primavera' por su clima?", "Vietnam", "Tailandia", "Indonesia", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentra el desierto de Atacama?", "América del Sur", "África", "Australia", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el canal artificial más largo del mundo?", "Gran Canal de China", "Canal de Suez", "Canal de Panamá", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido por sus tulipanes y molinos de viento?", "Países Bajos (Holanda)", "Bélgica", "Dinamarca", "*1"});
        questionThemes[2].add(new String[]{"¿Qué cordillera divide Francia de España?", "Pirineos", "Alpes", "Montes Jura", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el Salto del Ángel, la cascada más alta del mundo?", "Venezuela", "Brasil", "Guyana", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido como 'la isla esmeralda'?", "Irlanda", "Escocia", "Gales", "*1"});
        questionThemes[2].add(new String[]{"¿Qué península forma la parte occidental de Dinamarca?", "Península de Jutlandia", "Península Escandinava", "Península Ibérica", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el Parque Nacional de Yellowstone?", "Estados Unidos", "Canadá", "México", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país africano fue colonizado por Bélgica y sufrió un genocidio en 1994?", "Ruanda", "Burundi", "República Democrática del Congo", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el estrecho que separa la isla de Gran Bretaña del continente europeo?", "Canal de la Mancha", "Estrecho de Dover", "Mar del Norte", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es famoso por la Gran Barrera de Coral?", "Australia", "Indonesia", "Filipinas", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el río más largo de Asia?", "Yangtsé", "Amarillo (Huang He)", "Mekong", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido como 'la tierra de los canguros'?", "Australia", "Nueva Zelanda", "Papúa Nueva Guinea", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país tiene costa en el Mar Caspio?", "Kazajistán", "Irán", "Rusia", "*1"}); //Varias opciones, se elige una
        questionThemes[2].add(new String[]{"¿Qué lago separa Estados Unidos y Canadá?", "Superior", "Ontario", "Michigan", "*1"});
        questionThemes[2].add(new String[]{"¿Qué volcán sepultó Pompeya en el año 79 d.C.?", "Vesubio", "Etna", "Estrómboli", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país alberga el fiordo más largo del mundo, Sognefjord?", "Noruega", "Chile", "Nueva Zelanda", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el punto más alto de África?", "Kilimanjaro", "Monte Kenia", "Ras Dashán", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el famoso lago Ness?", "Escocia (Reino Unido)", "Irlanda", "Islandia", "*1"});
        questionThemes[2].add(new String[]{"¿Qué desierto atraviesa varios países del sur de África, incluyendo Namibia y Botsuana?", "Kalahari", "Namib", "Karoo", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Suiza?", "Berna", "Zúrich", "Ginebra", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país es conocido como 'la tierra del fuego'?", "Argentina", "Chile", "Islandia", "*1"});
        questionThemes[2].add(new String[]{"¿En qué continente se encuentra la Península Ibérica?", "Europa", "Asia", "África", "*1"});
        questionThemes[2].add(new String[]{"¿Qué país alberga el Gran Cañón del Colorado?", "Estados Unidos", "México", "Canadá", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es la capital de Brasil?", "Brasilia", "Río de Janeiro", "São Paulo", "*1"});
        questionThemes[2].add(new String[]{"¿Qué estrecho separa la Península de Malaca de la isla de Sumatra?", "Estrecho de Malaca", "Estrecho de Sunda", "Estrecho de Lombok", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra el Taj Mahal?", "India", "Pakistán", "Bangladés", "*1"});
        questionThemes[2].add(new String[]{"¿Cuál es el segundo país más grande del mundo por superficie?", "Canadá", "China", "Estados Unidos", "*1"});
        questionThemes[2].add(new String[]{"¿Qué mar baña las costas de Grecia?", "Mar Egeo", "Mar Jónico", "Mar Mediterráneo", "*1"});
        questionThemes[2].add(new String[]{"¿En qué país se encuentra la ciudad de Machu Picchu?", "Perú", "Bolivia", "Ecuador", "*1"});

        // Artes, Deportes y Entretenimiento
        questionThemes[3].add(new String[]{"¿Quién pintó la Mona Lisa?", "Leonardo da Vinci", "Miguel Ángel", "Rafael", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega en Wimbledon?", "Tenis", "Críquet", "Golf", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'Don Quijote de la Mancha'?", "Miguel de Cervantes", "Lope de Vega", "Calderón de la Barca", "*1"});
        questionThemes[3].add(new String[]{"¿Qué banda lanzó el álbum 'Abbey Road'?", "The Beatles", "The Rolling Stones", "Pink Floyd", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se practica en el Tour de Francia?", "Ciclismo", "Atletismo", "Automovilismo", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'Romeo y Julieta'?", "William Shakespeare", "Christopher Marlowe", "Ben Jonson", "*1"});
        questionThemes[3].add(new String[]{"¿Qué movimiento artístico caracteriza a 'La noche estrellada'?", "Postimpresionismo", "Impresionismo", "Expresionismo", "*1"});
        questionThemes[3].add(new String[]{"¿Cada cuántos años se celebran los Juegos Olímpicos de verano?", "4", "2", "6", "*1"});
        questionThemes[3].add(new String[]{"¿Quién dirigió 'Parque Jurásico' (Jurassic Park)?", "Steven Spielberg", "James Cameron", "George Lucas", "*1"});
        questionThemes[3].add(new String[]{"¿Qué artista musical fue conocido como 'El Rey del Pop'?", "Michael Jackson", "Prince", "Elvis Presley", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'Cien años de soledad'?", "Gabriel García Márquez", "Mario Vargas Llosa", "Isabel Allende", "*1"});
        questionThemes[3].add(new String[]{"¿En qué país se originó el tango?", "Argentina", "Uruguay", "Brasil", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió '1984'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "*1"});
        questionThemes[3].add(new String[]{"¿Qué técnica pictórica caracteriza a 'Las Meninas'?", "Barroco", "Renacimiento", "Impresionismo", "*1"});
        questionThemes[3].add(new String[]{"¿Qué artista es conocida como 'La Reina del Pop'?", "Madonna", "Lady Gaga", "Beyoncé", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte profesional se juega en la NBA?", "Baloncesto", "Fútbol Americano", "Béisbol", "*1"});
        questionThemes[3].add(new String[]{"¿Qué director es conocido por películas como 'Origen' (Inception) y 'El caballero oscuro' (The Dark Knight)?", "Christopher Nolan", "Quentin Tarantino", "Ridley Scott", "*1"});
        questionThemes[3].add(new String[]{"¿En qué ciudad se encuentra el Coliseo Romano?", "Roma", "Atenas", "Florencia", "*1"});
        questionThemes[3].add(new String[]{"¿Qué película ganó el premio Óscar a la Mejor Película en 1994?", "Forrest Gump", "Pulp Fiction", "Cadena perpetua (The Shawshank Redemption)", "*1"});
        questionThemes[3].add(new String[]{"¿Qué estilo artístico caracteriza principalmente la obra de Pablo Picasso?", "Cubismo", "Surrealismo", "Impresionismo", "*1"});
        questionThemes[3].add(new String[]{"¿En qué país se inventó el fútbol moderno?", "Inglaterra", "Brasil", "Italia", "*1"});
        questionThemes[3].add(new String[]{"¿Quién creó el personaje de Sherlock Holmes?", "Arthur Conan Doyle", "Agatha Christie", "Edgar Allan Poe", "*1"});
        questionThemes[3].add(new String[]{"¿En qué país se jugó la primera Copa Mundial de la FIFA?", "Uruguay", "Brasil", "Italia", "*1"});
        questionThemes[3].add(new String[]{"¿Qué artista es famoso por sus obras surrealistas, como 'La persistencia de la memoria'?", "Salvador Dalí", "René Magritte", "Joan Miró", "*1"});
        questionThemes[3].add(new String[]{"¿Quién es el autor de la saga de libros de Harry Potter?", "J.K. Rowling", "J.R.R. Tolkien", "Stephen King", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte practicó profesionalmente Michael Jordan antes de dedicarse al béisbol por un breve período?", "Baloncesto", "Béisbol", "Golf", "*1"});
        questionThemes[3].add(new String[]{"¿Quién compuso la ópera 'Carmen'?", "Georges Bizet", "Giuseppe Verdi", "Wolfgang Amadeus Mozart", "*1"});
        questionThemes[3].add(new String[]{"¿En qué ciudad se encuentra el museo del Louvre?", "París", "Roma", "Londres", "*1"});
        questionThemes[3].add(new String[]{"¿Cómo se llama el clásico del fútbol español entre el Real Madrid y el FC Barcelona?", "El Clásico", "El Derbi Madrileño", "El Gran Derbi", "*1"});
        questionThemes[3].add(new String[]{"¿Quién dirigió la película 'Titanic'?", "James Cameron", "Steven Spielberg", "Christopher Nolan", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'El Señor de los Anillos'?", "J.R.R. Tolkien", "C.S. Lewis", "George R.R. Martin", "*1"});
        questionThemes[3].add(new String[]{"¿Qué álbum de Michael Jackson incluye la canción 'Thriller'?", "Thriller", "Bad", "Off the Wall", "*1"});
        questionThemes[3].add(new String[]{"¿Qué selección nacional de rugby es conocida como los 'All Blacks'?", "Nueva Zelanda", "Australia", "Sudáfrica", "*1"});
        questionThemes[3].add(new String[]{"¿Quién compuso la ópera 'La Traviata'?", "Giuseppe Verdi", "Richard Wagner", "Gioachino Rossini", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega en el estadio Maracaná en Río de Janeiro, Brasil?", "Fútbol", "Voleibol", "Atletismo", "*1"});
        questionThemes[3].add(new String[]{"¿En qué país se encuentra el Monte Fuji?", "Japón", "China", "Corea del Sur", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'El Principito'?", "Antoine de Saint-Exupéry", "Albert Camus", "Jean-Paul Sartre", "*1"});
        questionThemes[3].add(new String[]{"¿Qué país es famoso por sus celebraciones de Carnaval, especialmente en Río de Janeiro?", "Brasil", "Colombia", "Venezuela", "*1"});
        questionThemes[3].add(new String[]{"¿Qué director es conocido por películas como 'Pulp Fiction' y 'Kill Bill'?", "Quentin Tarantino", "Martin Scorsese", "David Fincher", "*1"});
        questionThemes[3].add(new String[]{"¿Qué instrumento musical toca el reconocido violonchelista Yo-Yo Ma?", "Violonchelo", "Violín", "Piano", "*1"});
        questionThemes[3].add(new String[]{"¿En qué año se estrenó la primera película de 'Star Wars' ('La guerra de las galaxias')?", "1977", "1980", "1983", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega con un disco llamado 'puck'?", "Hockey sobre hielo", "Hockey sobre césped", "Curling", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió la novela 'Crimen y castigo'?", "Fiódor Dostoyevski", "León Tolstói", "Antón Chéjov", "*1"});
        questionThemes[3].add(new String[]{"¿Qué película surcoreana ganó el premio Óscar a la Mejor Película en 2020?", "Parásitos", "Burning", "Okja", "*1"});
        questionThemes[3].add(new String[]{"¿Quién pintó el techo de la Capilla Sixtina en el Vaticano?", "Miguel Ángel", "Rafael", "Botticelli", "*1"});
        questionThemes[3].add(new String[]{"¿Qué instrumento musical es central en el flamenco?", "Guitarra española", "Cajón flamenco", "Baile", "*1"});
        questionThemes[3].add(new String[]{"¿Cuál fue la primera película animada de Disney en presentar una princesa?", "Blancanieves y los siete enanitos", "Cenicienta", "La bella durmiente", "*1"});
        questionThemes[3].add(new String[]{"¿Qué escultor francés creó 'El pensador'?", "Auguste Rodin", "Michelangelo", "Donatello", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte extremo implica trucos como 'ollies' y 'flips'?", "Skateboarding (Patineta)", "BMX", "Snowboarding", "*1"});
        questionThemes[3].add(new String[]{"¿Qué director mexicano es conocido por películas como 'El laberinto del fauno' y 'La forma del agua'?", "Guillermo del Toro", "Alfonso Cuarón", "Alejandro González Iñárritu", "*1"});
        questionThemes[3].add(new String[]{"¿Quién es el autor de la serie de libros 'Las Crónicas de Narnia'?", "C.S. Lewis", "J.R.R. Tolkien", "Philip Pullman", "*1"});
        questionThemes[3].add(new String[]{"¿En qué país se originó la danza-lucha conocida como capoeira?", "Brasil", "Angola", "Portugal", "*1"});
        questionThemes[3].add(new String[]{"¿Qué pintor es famoso por sus representaciones de relojes derretidos?", "Salvador Dalí", "Pablo Picasso", "Joan Miró", "*1"});
        questionThemes[3].add(new String[]{"¿Con qué frase comienza la novela 'Moby Dick'?", "Llamadme Ismael", "Era el mejor de los tiempos, era el peor de los tiempos...", "Hace mucho, mucho tiempo...", "*1"});
        questionThemes[3].add(new String[]{"¿Qué película de Pixar trata sobre una rata que sueña con ser chef?", "Ratatouille", "Buscando a Nemo", "Toy Story", "*1"});
        questionThemes[3].add(new String[]{"¿Qué país ganó la primera Copa Mundial de Fútbol en 1930?", "Uruguay", "Argentina", "Brasil", "*1"});
        questionThemes[3].add(new String[]{"¿Quién es considerado uno de los padres del jazz?", "Louis Armstrong", "Duke Ellington", "Charlie Parker", "*1"});
        questionThemes[3].add(new String[]{"¿Qué escritor argentino es conocido por sus cuentos fantásticos y laberínticos?", "Jorge Luis Borges", "Julio Cortázar", "Ernesto Sabato", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega con raquetas y una red, golpeando una pelota pequeña de un lado a otro?", "Tenis", "Bádminton", "Squash", "*1"});
        questionThemes[3].add(new String[]{"¿De qué película es la famosa frase 'Volveré' ('I'll be back')?", "Terminator", "Depredador (Predator)", "RoboCop", "*1"});
        questionThemes[3].add(new String[]{"¿Qué serie de televisión tiene como protagonista a Walter White?", "Breaking Bad", "Los Soprano (The Sopranos)", "The Wire", "*1"});
        questionThemes[3].add(new String[]{"¿Qué famoso estadio de fútbol se encuentra en Londres y es la sede de la selección inglesa?", "Wembley", "Old Trafford", "Anfield", "*1"});
        questionThemes[3].add(new String[]{"¿Quién compuso e interpretó la canción 'Imagine'?", "John Lennon", "Paul McCartney", "George Harrison", "*1"});
        questionThemes[3].add(new String[]{"¿Qué estilo de danza clásica se caracteriza por movimientos gráciles, puntas y elevaciones?", "Ballet", "Flamenco", "Hip hop", "*1"});
        questionThemes[3].add(new String[]{"¿En qué ciudad de Francia se celebra el Festival Internacional de Cine de Cannes?", "Cannes", "París", "Niza", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega en una cancha con dos canastas o cestos?", "Baloncesto", "Fútbol", "Balonmano", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió la novela 'La casa de los espíritus'?", "Isabel Allende", "Gabriel García Márquez", "Laura Esquivel", "*1"});
        questionThemes[3].add(new String[]{"¿Qué músico es considerado 'El Rey del Rock and Roll'?", "Elvis Presley", "Chuck Berry", "Little Richard", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte es conocido como 'el deporte rey' y se juega con un balón esférico entre dos equipos?", "Fútbol", "Béisbol", "Baloncesto", "*1"});
        questionThemes[3].add(new String[]{"¿De qué trata la película de Pixar 'Coco'?", "El Día de Muertos en México", "Un robot que se enamora", "Un anciano que viaja en globo", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió la novela 'El gran Gatsby'?", "F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "*1"});
        questionThemes[3].add(new String[]{"¿Qué muralista mexicano pintó 'El hombre controlador del universo' en el Palacio de Bellas Artes?", "Diego Rivera", "José Clemente Orozco", "David Alfaro Siqueiros", "*1"});
        questionThemes[3].add(new String[]{"¿Quién interpretó la canción principal de la película 'Skyfall' de James Bond?", "Adele", "Sam Smith", "Billie Eilish", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte incluye torneos llamados 'Grand Slam'?", "Tenis", "Golf", "Bádminton", "*1"});
        questionThemes[3].add(new String[]{"¿Qué artista mexicana es conocida por sus autorretratos, como 'Las dos Fridas'?", "Frida Kahlo", "Remedios Varo", "Leonora Carrington", "*1"});
        questionThemes[3].add(new String[]{"¿Quién pintó 'La última cena'?", "Leonardo da Vinci", "Rafael", "Caravaggio", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió 'Orgullo y prejuicio'?", "Jane Austen", "Charlotte Brontë", "Emily Brontë", "*1"});
        questionThemes[3].add(new String[]{"¿Qué obra de teatro de William Shakespeare presenta al príncipe Hamlet?", "Hamlet", "Macbeth", "Romeo y Julieta", "*1"});
        questionThemes[3].add(new String[]{"¿Qué objeto utiliza un director de orquesta para dirigir a los músicos?", "Batuta", "Arco", "Púa", "*1"});
        questionThemes[3].add(new String[]{"¿Qué país ganó la mayor cantidad de medallas de oro en los Juegos Olímpicos de Tokio 2020?", "Estados Unidos", "China", "Japón", "*1"});
        questionThemes[3].add(new String[]{"¿De qué película animada de Pixar es el personaje principal un pez payaso llamado Nemo?", "Buscando a Nemo", "Bichos", "Monsters, Inc.", "*1"});
        questionThemes[3].add(new String[]{"¿Qué género musical popularizó Elvis Presley en la década de 1950?", "Rock and Roll", "Blues", "Country", "*1"});
        questionThemes[3].add(new String[]{"¿Quién escribió la aclamada novela 'Matar a un ruiseñor'?", "Harper Lee", "Truman Capote", "William Faulkner", "*1"});
        questionThemes[3].add(new String[]{"¿Qué obra épica de la literatura griega antigua narra la Guerra de Troya?", "La Ilíada", "La Odisea", "La Eneida", "*1"});
        questionThemes[3].add(new String[]{"¿Qué instrumento musical tocaba el famoso trompetista de jazz Miles Davis?", "Trompeta", "Saxofón", "Trombón", "*1"});
        questionThemes[3].add(new String[]{"¿Quién dirigió la trilogía original de 'La Guerra de las Galaxias'?", "George Lucas", "Steven Spielberg", "Irvin Kershner", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte se juega con un bate, una pelota y guantes, con equipos que intentan anotar carreras?", "Béisbol", "Críquet", "Softbol", "*1"});
        questionThemes[3].add(new String[]{"¿Quién es considerado uno de los más grandes dramaturgos ingleses, autor de 'Hamlet' y 'Macbeth'?", "William Shakespeare", "Christopher Marlowe", "Ben Jonson", "*1"});
        questionThemes[3].add(new String[]{"¿Qué famoso ballet ruso se caracteriza por la danza de los cisnes?", "El lago de los cisnes", "El Cascanueces", "La bella durmiente", "*1"});
        questionThemes[3].add(new String[]{"¿Qué banda británica de rock lanzó álbumes icónicos como 'The Dark Side of the Moon' y 'Wish You Were Here'?", "Pink Floyd", "Led Zeppelin", "The Who", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte de equipo se juega en una cancha dividida por una red, donde los jugadores golpean una pelota con las manos?", "Voleibol", "Balonmano", "Tenis de mesa", "*1"});
        questionThemes[3].add(new String[]{"¿Quién es el autor de la distópica novela 'Un mundo feliz' (Brave New World)?", "Aldous Huxley", "George Orwell", "Ray Bradbury", "*1"});
        questionThemes[3].add(new String[]{"¿Qué película de ciencia ficción de Ridley Scott presenta al icónico personaje del xenomorfo?", "Alien: el octavo pasajero", "Blade Runner", "Prometheus", "*1"});
        questionThemes[3].add(new String[]{"¿Qué estilo arquitectónico se caracteriza por sus grandes catedrales con arcos apuntados y vidrieras de colores?", "Gótico", "Románico", "Barroco", "*1"});
        questionThemes[3].add(new String[]{"¿Qué famoso director de cine es conocido por sus películas de suspenso y terror, como 'Psicosis' y 'Los pájaros'?", "Alfred Hitchcock", "Stanley Kubrick", "Orson Welles", "*1"});
        questionThemes[3].add(new String[]{"¿Qué instrumento de viento metal se utiliza comúnmente en el jazz y la música clásica?", "Trompeta", "Trombón", "Saxofón", "*1"});
        questionThemes[3].add(new String[]{"¿Qué famoso compositor alemán es conocido por sus sinfonías, como la 'Quinta Sinfonía' y la 'Novena Sinfonía'?", "Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "*1"});
        questionThemes[3].add(new String[]{"¿Qué deporte de invierno se practica con una tabla sobre la nieve, realizando trucos y descensos en pendientes?", "Snowboarding", "Esquí", "Hockey sobre hielo", "*1"});
        questionThemes[3].add(new String[]{"¿Qué famoso pintor neerlandés postimpresionista pintó 'Los girasoles'?", "Vincent van Gogh", "Claude Monet", "Paul Cézanne", "*1"});
        questionThemes[3].add(new String[]{"¿Qué dramaturgo noruego escribió obras de teatro realistas como 'Casa de muñecas' y 'Espectros'?", "Henrik Ibsen", "August Strindberg", "Antón Chéjov", "*1"});

        //Ciencias y Tecnología
        questionThemes[4].add(new String[]{"¿Cuál es el elemento más abundante en el universo?", "Hidrógeno", "Oxígeno", "Helio", "*1"});
        questionThemes[4].add(new String[]{"¿Quién formuló la teoría de la relatividad?", "Albert Einstein", "Isaac Newton", "Stephen Hawking", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es la fórmula química del agua?", "H2O", "CO2", "NaCl", "*1"});
        questionThemes[4].add(new String[]{"¿Qué planeta es conocido como el 'planeta rojo'?", "Marte", "Venus", "Júpiter", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es la unidad básica de la herencia?", "Gen", "Célula", "Átomo", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico desarrolló las leyes del movimiento?", "Isaac Newton", "Galileo Galilei", "Johannes Kepler", "*1"});
        questionThemes[4].add(new String[]{"¿Qué proceso utilizan las plantas para producir su propio alimento?", "Fotosíntesis", "Respiración", "Fermentación", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es el metal más abundante en la corteza terrestre?", "Aluminio", "Hierro", "Cobre", "*1"});
        questionThemes[4].add(new String[]{"¿Qué enfermedad se caracteriza por la pérdida de memoria y otras capacidades cognitivas?", "Alzheimer", "Parkinson", "Esclerosis múltiple", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología utiliza ondas de radio para detectar objetos?", "Radar", "Sonar", "Láser", "*1"});
        questionThemes[4].add(new String[]{"¿Quién inventó la bombilla incandescente?", "Thomas Edison", "Nikola Tesla", "Alexander Graham Bell", "*1"});
        questionThemes[4].add(new String[]{"¿Qué fuerza mantiene a los planetas en órbita alrededor del Sol?", "Gravedad", "Magnetismo", "Fricción", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es el componente principal del aire que respiramos?", "Nitrógeno", "Oxígeno", "Dióxido de carbono", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico propuso la teoría de la evolución por selección natural?", "Charles Darwin", "Jean-Baptiste Lamarck", "Gregor Mendel", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es la velocidad de la luz en el vacío?", "Aproximadamente 300,000 km/s", "Aproximadamente 150,000 km/s", "Aproximadamente 600,000 km/s", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de energía se obtiene del movimiento del agua?", "Hidroeléctrica", "Solar", "Eólica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estructura del cuerpo humano se encarga de bombear la sangre?", "Corazón", "Pulmones", "Cerebro", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología permite la comunicación inalámbrica a corta distancia entre dispositivos?", "Bluetooth", "Wi-Fi", "Infrarrojo", "*1"});
        questionThemes[4].add(new String[]{"¿Qué rama de la biología estudia los seres vivos y su relación con el medio ambiente?", "Ecología", "Genética", "Zoología", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico descubrió la penicilina?", "Alexander Fleming", "Louis Pasteur", "Robert Koch", "*1"});
        questionThemes[4].add(new String[]{"¿Cuál es el planeta más grande de nuestro sistema solar?", "Júpiter", "Saturno", "Urano", "*1"});
        questionThemes[4].add(new String[]{"¿Qué gas protege la Tierra de la radiación ultravioleta del Sol?", "Ozono", "Dióxido de carbono", "Metano", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estudia la geología?", "La Tierra", "Los astros", "Los seres vivos", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico formuló las leyes de la herencia?", "Gregor Mendel", "Charles Darwin", "Thomas Hunt Morgan", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es la fusión nuclear?", "Unión de núcleos atómicos", "División de núcleos atómicos", "Reacción química", "*1"});
        questionThemes[4].add(new String[]{"¿Qué aparato se utiliza para medir la presión atmosférica?", "Barómetro", "Termómetro", "Higrómetro", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de energía se obtiene del viento?", "Eólica", "Solar", "Geotérmica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué órgano del cuerpo humano produce la insulina?", "Páncreas", "Hígado", "Riñones", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología permite la transmisión de información a través de fibra óptica?", "Luz", "Ondas de radio", "Microondas", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico desarrolló la teoría del Big Bang?", "Georges Lemaître", "Edwin Hubble", "Stephen Hawking", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estudia la astronomía?", "Los cuerpos celestes", "La Tierra", "Los seres vivos", "*1"});
        questionThemes[4].add(new String[]{"¿Qué gas es responsable del efecto invernadero en mayor medida?", "Dióxido de carbono", "Metano", "Vapor de agua", "*1"});
        questionThemes[4].add(new String[]{"¿Qué hueso protege el cerebro?", "Cráneo", "Fémur", "Columna vertebral", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología se utiliza en los códigos de barras?", "Láser", "Radiofrecuencia", "Infrarrojo", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico descubrió la estructura del ADN?", "James Watson y Francis Crick", "Rosalind Franklin", "Maurice Wilkins", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de energía se obtiene del calor interno de la Tierra?", "Geotérmica", "Nuclear", "Biomasa", "*1"});
        questionThemes[4].add(new String[]{"¿Qué órgano del cuerpo humano filtra la sangre y produce la orina?", "Riñones", "Hígado", "Pulmones", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología se utiliza en los teléfonos móviles para determinar su ubicación?", "GPS", "Wi-Fi", "Bluetooth", "*1"});
        questionThemes[4].add(new String[]{"¿Qué rama de la física estudia el movimiento?", "Mecánica", "Óptica", "Termodinámica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico formuló la teoría de la tectónica de placas?", "Alfred Wegener", "Harry Hess", "John Tuzo Wilson", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es un agujero negro?", "Región del espacio con gravedad extrema", "Estrella enana blanca", "Nebulosa planetaria", "*1"});
        questionThemes[4].add(new String[]{"¿Qué gas utilizan las plantas para realizar la fotosíntesis?", "Dióxido de carbono", "Oxígeno", "Nitrógeno", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de roca se forma a partir del enfriamiento del magma?", "Ígnea", "Sedimentaria", "Metamórfica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico inventó el telescopio reflector?", "Isaac Newton", "Galileo Galilei", "Johannes Kepler", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es la clonación?", "Creación de copias genéticamente idénticas", "Manipulación genética", "Selección artificial", "*1"});
        questionThemes[4].add(new String[]{"¿Qué unidad se utiliza para medir la intensidad del sonido?", "Decibelio", "Hercio", "Amperio", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de onda electromagnética se utiliza en los hornos microondas?", "Microondas", "Rayos X", "Ondas de radio", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico propuso la teoría heliocéntrica del sistema solar?", "Nicolás Copérnico", "Galileo Galilei", "Ptolomeo", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es la mitosis?", "División celular", "Fusión nuclear", "Reacción química", "*1"});
        questionThemes[4].add(new String[]{"¿Qué se utiliza para medir la resistencia eléctrica?", "Ohmio", "Voltio", "Amperio", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología permite la edición genética precisa?", "CRISPR", "PCR", "ARN de interferencia", "*1"});
        questionThemes[4].add(new String[]{"¿Qué proceso convierte el azúcar en alcohol y dióxido de carbono?", "Fermentación", "Fotosíntesis", "Respiración celular", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estudia la entomología?", "Los insectos", "Las aves", "Los mamíferos", "*1"});
        questionThemes[4].add(new String[]{"¿Qué fuerza es responsable de las mareas?", "La gravedad de la Luna", "El magnetismo de la Tierra", "La rotación de la Tierra", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología utilizan los CDs y DVDs para almacenar información?", "Láser", "Magnetismo", "Ondas de radio", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico desarrolló la tabla periódica de los elementos?", "Dmitri Mendeléyev", "Antoine Lavoisier", "John Dalton", "*1"});
        questionThemes[4].add(new String[]{"¿Qué proceso transforma la energía luminosa en energía química en las plantas?", "Fotosíntesis", "Respiración celular", "Quimiosíntesis", "*1"});
        questionThemes[4].add(new String[]{"¿Qué parte del átomo tiene carga negativa?", "Electrón", "Protón", "Neutrón", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología permite la comunicación a través de internet?", "Protocolo TCP/IP", "Bluetooth", "Infrarrojo", "*1"});
        questionThemes[4].add(new String[]{"¿Qué rama de la medicina estudia las enfermedades del corazón?", "Cardiología", "Neurología", "Oncología", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es la nanotecnología?", "Manipulación de la materia a escala atómica y molecular", "Estudio de los fósiles", "Exploración espacial", "*1"});
        questionThemes[4].add(new String[]{"¿Qué gas es esencial para la respiración de los animales?", "Oxígeno", "Dióxido de carbono", "Nitrógeno", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estudia la paleontología?", "Los fósiles", "Las estrellas", "Los átomos", "*1"});
        questionThemes[4].add(new String[]{"¿Qué científico observó por primera vez células usando un microscopio?", "Robert Hooke", "Antonie van Leeuwenhoek", "Matthias Schleiden", "*1"});
        questionThemes[4].add(new String[]{"¿Qué es un transgénico?", "Organismo modificado genéticamente", "Organismo que se reproduce sexualmente", "Organismo que vive en simbiosis", "*1"});
        questionThemes[4].add(new String[]{"¿Qué unidad se utiliza para medir la frecuencia?", "Hercio", "Amperio", "Voltio", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de radiación se utiliza en las radiografías?", "Rayos X", "Rayos gamma", "Rayos ultravioleta", "*1"});
        questionThemes[4].add(new String[]{"¿Quién descubrió la estructura del benceno?", "Friedrich August Kekulé", "Michael Faraday", "Joseph Priestley", "*1"});
        questionThemes[4].add(new String[]{"¿Qué proceso biológico permite la adaptación de las especies a su entorno?", "Evolución", "Mutación", "Selección natural", "*1"});
        questionThemes[4].add(new String[]{"¿Qué mide el voltímetro?", "Diferencia de potencial eléctrico (voltaje)", "Corriente eléctrica (amperaje)", "Resistencia eléctrica (ohmios)", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología se utiliza en la creación de hologramas?", "Láser", "Microondas", "Ultrasonido", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tipo de energía almacenan las baterías?", "Química", "Eléctrica", "Mecánica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué estudia la ornitología?", "Las aves", "Los peces", "Los reptiles", "*1"});
        questionThemes[4].add(new String[]{"¿Qué planeta es conocido por sus anillos?", "Saturno", "Júpiter", "Urano", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología utilizan los controles remotos para comunicarse con los aparatos electrónicos?", "Infrarrojo", "Bluetooth", "Wi-Fi", "*1"});
        questionThemes[4].add(new String[]{"¿Quién postuló las leyes de la termodinámica?", "Sadi Carnot, James Prescott Joule, Rudolf Clausius", "Isaac Newton", "Albert Einstein", "*1"});
        questionThemes[4].add(new String[]{"¿Qué proceso permite la transmisión de información genética de padres a hijos?", "Herencia", "Mutación", "Adaptación", "*1"});
        questionThemes[4].add(new String[]{"¿Qué partícula subatómica tiene carga positiva?", "Protón", "Electrón", "Neutrón", "*1"});
        questionThemes[4].add(new String[]{"¿Qué tecnología hace posible el funcionamiento de los ordenadores cuánticos?", "Mecánica cuántica", "Relatividad general", "Física clásica", "*1"});
        questionThemes[4].add(new String[]{"¿Qué rama de la biología se encarga del estudio de los hongos?", "Micología", "Botánica", "Zoología", "*1"});

        //Cultura general
        questionThemes[5].add(new String[]{"¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Yangtsé", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año comenzó la Primera Guerra Mundial?", "1914", "1917", "1919", "*1"});
        questionThemes[5].add(new String[]{"¿Quién pintó 'Las Meninas'?", "Diego Velázquez", "Francisco de Goya", "El Greco", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Australia?", "Canberra", "Sídney", "Melbourne", "*1"});
        questionThemes[5].add(new String[]{"¿Qué elemento químico representa el símbolo 'Au'?", "Oro", "Plata", "Plomo", "*1"});
        questionThemes[5].add(new String[]{"¿Quién escribió 'Hamlet'?", "William Shakespeare", "Christopher Marlowe", "Ben Jonson", "*1"});
        questionThemes[5].add(new String[]{"¿Qué océano baña las costas de California?", "Océano Pacífico", "Océano Atlántico", "Océano Índico", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?", "1776", "1789", "1812", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso científico formuló la teoría de la gravedad universal?", "Isaac Newton", "Albert Einstein", "Galileo Galilei", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el idioma oficial de Brasil?", "Portugués", "Español", "Inglés", "*1"});
        questionThemes[5].add(new String[]{"¿Qué civilización antigua construyó las pirámides de Giza?", "Egipcia", "Maya", "Azteca", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso compositor escribió la 'Novena Sinfonía'?", "Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la moneda oficial de la Unión Europea?", "Euro", "Libra esterlina", "Franco suizo", "*1"});
        questionThemes[5].add(new String[]{"¿Qué cordillera separa Europa de Asia?", "Montes Urales", "Himalaya", "Alpes", "*1"});
        questionThemes[5].add(new String[]{"¿Quién fue el primer presidente de los Estados Unidos?", "George Washington", "Thomas Jefferson", "Abraham Lincoln", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso pintor español creó el estilo cubista junto a Georges Braque?", "Pablo Picasso", "Salvador Dalí", "Joan Miró", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el lago más grande del mundo (por superficie)?", "Mar Caspio", "Lago Superior", "Lago Victoria", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año comenzó la Revolución Francesa?", "1789", "1776", "1815", "*1"});
        questionThemes[5].add(new String[]{"¿Qué emperador romano legalizó el cristianismo en el Imperio Romano?", "Constantino I", "Nerón", "Julio César", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Japón?", "Tokio", "Kioto", "Osaka", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso dramaturgo noruego escribió 'Casa de muñecas'?", "Henrik Ibsen", "August Strindberg", "Anton Chéjov", "*1"});
        questionThemes[5].add(new String[]{"¿Qué desierto es conocido como el más grande del mundo (no polar)?", "Desierto del Sahara", "Desierto de Gobi", "Desierto de Atacama", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se produjo la llegada de Cristóbal Colón a América?", "1492", "1500", "1519", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa reina de Inglaterra gobernó durante gran parte del siglo XVI?", "Isabel I", "María I", "Isabel II", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el planeta más cercano al Sol?", "Mercurio", "Venus", "Marte", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escultor francés creó 'El pensador'?", "Auguste Rodin", "Michelangelo", "Donatello", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el río más largo de Europa?", "Volga", "Danubio", "Rin", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se firmó el armisticio que puso fin a la Primera Guerra Mundial?", "1918", "1917", "1919", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso físico alemán formuló la teoría de la relatividad especial y general?", "Albert Einstein", "Isaac Newton", "Max Planck", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Italia?", "Roma", "Milán", "Florencia", "*1"});
        questionThemes[5].add(new String[]{"¿Qué antigua civilización mesoamericana construyó la ciudad de Teotihuacán?", "Aztecas", "Mayas", "Olmecas", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso compositor austriaco compuso 'La flauta mágica'?", "Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la moneda oficial de Japón?", "Yen", "Won", "Yuan", "*1"});
        questionThemes[5].add(new String[]{"¿Qué estrecho separa la península ibérica de África?", "Estrecho de Gibraltar", "Estrecho de Bering", "Estrecho de Magallanes", "*1"});
        questionThemes[5].add(new String[]{"¿Quién fue el primer presidente de Sudáfrica tras el apartheid?", "Nelson Mandela", "Desmond Tutu", "F. W. de Klerk", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso pintor neerlandés pintó 'La noche estrellada'?", "Vincent van Gogh", "Rembrandt", "Johannes Vermeer", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la montaña más alta de África?", "Kilimanjaro", "Monte Kenia", "Monte Atlas", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año comenzó la Guerra Civil Española?", "1936", "1939", "1931", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso general romano cruzó los Alpes con elefantes para atacar a Roma?", "Aníbal", "Julio César", "Escipión el Africano", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Canadá?", "Ottawa", "Toronto", "Montreal", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa escritora británica creó el personaje de Miss Marple?", "Agatha Christie", "Arthur Conan Doyle", "Jane Austen", "*1"});
        questionThemes[5].add(new String[]{"¿Qué archipiélago japonés fue bombardeado con armas nucleares al final de la Segunda Guerra Mundial?", "Hiroshima y Nagasaki", "Tokio y Kioto", "Osaka y Kobe", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se produjo la caída del Imperio Romano de Occidente?", "476 d.C.", "395 d.C.", "1453 d.C.", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa científica polaca-francesa fue pionera en el estudio de la radioactividad?", "Marie Curie", "Rosalind Franklin", "Lise Meitner", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el planeta con mayor cantidad de satélites naturales?", "Saturno", "Júpiter", "Urano", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso compositor italiano compuso 'Las Cuatro Estaciones'?", "Antonio Vivaldi", "Johann Sebastian Bach", "George Frideric Handel", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el país más extenso de América del Sur?", "Brasil", "Argentina", "Colombia", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se inició la Guerra Fría?", "1947", "1945", "1953", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso líder pacifista indio lideró la independencia de la India?", "Mahatma Gandhi", "Jawaharlal Nehru", "Sardar Vallabhbhai Patel", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de México?", "Ciudad de México", "Guadalajara", "Monterrey", "*1"});
        questionThemes[5].add(new String[]{"¿Qué antigua civilización precolombina construyó la ciudad de Machu Picchu?", "Incas", "Mayas", "Aztecas", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso pintor español pintó 'El Guernica'?", "Pablo Picasso", "Salvador Dalí", "Joan Miró", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la moneda oficial del Reino Unido?", "Libra esterlina", "Euro", "Dólar estadounidense", "*1"});
        questionThemes[5].add(new String[]{"¿Qué canal artificial conecta el Mar Rojo con el Mar Mediterráneo?", "Canal de Suez", "Canal de Panamá", "Canal de Corinto", "*1"});
        questionThemes[5].add(new String[]{"¿Quién fue el primer hombre en viajar al espacio?", "Yuri Gagarin", "Neil Armstrong", "Buzz Aldrin", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escritor estadounidense escribió 'El gran Gatsby'?", "F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el punto más profundo de los océanos?", "Fosa de las Marianas", "Fosa de Tonga", "Fosa de Kermadec", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se produjo la Revolución Rusa?", "1917", "1905", "1922", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso emperador francés lideró numerosas campañas militares a principios del siglo XIX?", "Napoleón Bonaparte", "Luis XIV", "Carlos Magno", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Rusia?", "Moscú", "San Petersburgo", "Novosibirsk", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa novela de Jane Austen comienza con la frase 'Es una verdad universalmente reconocida...'?", "Orgullo y prejuicio", "Sentido y sensibilidad", "Emma", "*1"});
        questionThemes[5].add(new String[]{"¿Qué volcán sepultó la ciudad romana de Pompeya en el año 79 d.C.?", "Vesubio", "Etna", "Estrómboli", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se firmó el Tratado de Versalles, que puso fin oficialmente a la Primera Guerra Mundial?", "1919", "1918", "1920", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa matemática británica es considerada la primera programadora de la historia?", "Ada Lovelace", "Grace Hopper", "Katherine Johnson", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el satélite natural de la Tierra?", "La Luna", "Fobos", "Deimos", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso compositor alemán compuso 'Para Elisa'?", "Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el país más pequeño de Europa?", "Ciudad del Vaticano", "Mónaco", "San Marino", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año comenzó la Segunda Guerra Mundial?", "1939", "1941", "1945", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso líder sudafricano luchó contra el apartheid?", "Nelson Mandela", "Desmond Tutu", "Steve Biko", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Argentina?", "Buenos Aires", "Córdoba", "Rosario", "*1"});
        questionThemes[5].add(new String[]{"¿Qué antigua civilización mesoamericana creó un calendario muy preciso?", "Mayas", "Aztecas", "Olmecas", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escritor irlandés escribió 'Drácula'?", "Bram Stoker", "Oscar Wilde", "Jonathan Swift", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la moneda oficial de Suiza?", "Franco suizo", "Euro", "Libra esterlina", "*1"});
        questionThemes[5].add(new String[]{"¿Qué estrecho separa Asia de América?", "Estrecho de Bering", "Estrecho de Magallanes", "Estrecho de Malaca", "*1"});
        questionThemes[5].add(new String[]{"¿Quién fue el primer ser vivo en orbitar la Tierra?", "Laika (perra)", "Yuri Gagarin (humano)", "Ham (chimpancé)", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escritor estadounidense escribió 'Matar a un ruiseñor'?", "Harper Lee", "Ernest Hemingway", "Mark Twain", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el lago más profundo del mundo?", "Lago Baikal", "Lago Tanganica", "Mar Caspio", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se produjo la Revolución Industrial en Gran Bretaña?", "Finales del siglo XVIII", "Mediados del siglo XVII", "Principios del siglo XIX", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso explorador portugués lideró la primera expedición marítima que rodeó el mundo?", "Fernando de Magallanes (comenzó) / Juan Sebastián Elcano (terminó)", "Vasco da Gama", "Bartolomeu Dias", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Polonia?", "Varsovia", "Cracovia", "Łódź", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escritor francés escribió 'Los miserables'?", "Victor Hugo", "Alexandre Dumas", "Gustave Flaubert", "*1"});
        questionThemes[5].add(new String[]{"¿Qué construcción antigua es considerada una de las Siete Maravillas del Mundo Antiguo y se encuentra en Egipto?", "Gran Pirámide de Giza", "Faro de Alejandría", "Jardines Colgantes de Babilonia", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se fundó la Organización de las Naciones Unidas (ONU)?", "1945", "1948", "1950", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso naturalista inglés desarrolló la teoría de la evolución por selección natural?", "Charles Darwin", "Alfred Russel Wallace", "Jean-Baptiste Lamarck", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el océano más frío de la Tierra?", "Océano Ártico", "Océano Antártico", "Océano Índico", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso pintor impresionista francés pintó 'Los nenúfares'?", "Claude Monet", "Edgar Degas", "Pierre-Auguste Renoir", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Sudáfrica?", "Pretoria (administrativa), Ciudad del Cabo (legislativa), Bloemfontein (judicial)", "Johannesburgo", "Durban", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año comenzó la Guerra de Vietnam?", "1955", "1965", "1975", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso líder soviético lideró la Unión Soviética durante la mayor parte de la Guerra Fría?", "Iósif Stalin", "Nikita Jrushchov", "Mijaíl Gorbachov", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Grecia?", "Atenas", "Salónica", "Patras", "*1"});
        questionThemes[5].add(new String[]{"¿Qué antigua civilización habitó la península de Yucatán en México?", "Mayas", "Aztecas", "Olmecas", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso escritor español escribió 'Cien años de soledad'?", "Gabriel García Márquez (aunque es colombiano, influyó mucho en la literatura en español)", "Miguel de Cervantes", "Federico García Lorca", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el río que atraviesa Londres?", "Támesis", "Sena", "Rin", "*1"});
        questionThemes[5].add(new String[]{"¿Qué importante evento histórico tuvo lugar en Francia en 1789?", "Revolución Francesa", "Guerra de los Cien Años", "Coronación de Napoleón", "*1"});
        questionThemes[5].add(new String[]{"¿Quién fue el primer ser humano en viajar al espacio exterior?", "Yuri Gagarin", "Neil Armstrong", "Valentina Tereshkova", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa escritora británica es autora de la saga de Harry Potter?", "J.K. Rowling", "J.R.R. Tolkien", "C.S. Lewis", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el segundo planeta más grande de nuestro sistema solar?", "Saturno", "Júpiter", "Urano", "*1"});
        questionThemes[5].add(new String[]{"¿En qué año se construyó el Muro de Berlín?", "1961", "1949", "1989", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso revolucionario cubano lideró la Revolución Cubana junto a Fidel Castro?", "Ernesto 'Che' Guevara", "Camilo Cienfuegos", "Raúl Castro", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es la capital de Portugal?", "Lisboa", "Oporto", "Faro", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso científico italiano mejoró el telescopio y realizó importantes observaciones astronómicas?", "Galileo Galilei", "Isaac Newton", "Johannes Kepler", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso compositor austriaco es conocido por obras como 'Don Giovanni' y 'Las bodas de Fígaro'?", "Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Joseph Haydn", "*1"});
        questionThemes[5].add(new String[]{"¿Cuál es el proceso por el cual las plantas convierten la luz solar en energía química?", "Fotosíntesis", "Respiración celular", "Quimiosíntesis", "*1"});
        questionThemes[5].add(new String[]{"¿Qué tratado puso fin a la Primera Guerra Mundial?", "Tratado de Versalles", "Tratado de Tordesillas", "Tratado de París", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famosa actriz estadounidense protagonizó la película 'Desayuno con diamantes'?", "Audrey Hepburn", "Marilyn Monroe", "Elizabeth Taylor", "*1"});
        questionThemes[5].add(new String[]{"¿Qué estilo arquitectónico se caracteriza por sus arcos ojivales y bóvedas de crucería?", "Gótico", "Románico", "Barroco", "*1"});
        questionThemes[5].add(new String[]{"¿Qué importante declaración de derechos humanos fue adoptada por las Naciones Unidas en 1948?", "Declaración Universal de los Derechos Humanos", "Declaración de Independencia de los Estados Unidos", "Declaración de los Derechos del Hombre y del Ciudadano", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso pintor español es conocido por su estilo surrealista y obras como 'La persistencia de la memoria'?", "Salvador Dalí", "Pablo Picasso", "Joan Miró", "*1"});
        questionThemes[5].add(new String[]{"¿Qué famoso músico de rock es conocido por ser el líder de la banda Queen?", "Freddie Mercury", "Mick Jagger", "David Bowie", "*1"});
        questionThemes[5].add(new String[]{"¿Qué importante evento deportivo se celebra cada cuatro años y reúne a atletas de todo el mundo?", "Juegos Olímpicos", "Copa Mundial de la FIFA", "Campeonato Mundial de Atletismo", "*1"});
    }



    public static boolean mostrarPregunta(int tema, Scanner scanner) {
        String[] question;

        // Busca una pregunta que no haya sido realizada, en esta parte si me ayude de IA para que el metodo identifique las preguntas ya relizadas y no se repitan
        do {
            question = questionThemes[tema - 1].poll();
            if (question == null) {
                return false;
            }
        } while (preguntasRealizadas.contains(question[0]));


        preguntasRealizadas.add(question[0]);

        System.out.println("Pregunta: " + question[0]);


        //lista para manejar las opciones de repuesta
        List<String> opciones = Arrays.asList(Arrays.copyOfRange(question, 1, 4));
        Collections.shuffle(opciones);

        int correcta = -1;
        for (int i = 0; i < opciones.size(); i++) {

            if (opciones.get(i).equals(question[Integer.parseInt(question[4].substring(1))])) {
                correcta = i + 1;
            }
            System.out.println((i + 1) + ". " + opciones.get(i));
        }

        int option = -1;
        do {
            System.out.print("Tu respuesta (1-3): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, introduce un número entre 1 y 3:");
                scanner.nextLine();
            }
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 3);

        if (option == correcta) {
            System.out.println("¡Correcto! Avanzas 2 km.\n");
            distanciaJugador += 2;
        } else {
            System.out.println("Incorrecto. Retrocedes 1 km y tu ignorancia aumenta en 1 punto.\n");
            distanciaJugador = Math.max(0, distanciaJugador - 1);
            nivelIgnorancia += 1;
        }

        return true;
    }

    public static void mostrarProgreso() {
        System.out.println("Estás en " + distanciaJugador + " km.");
        System.out.println("Nivel de ignorancia: " + nivelIgnorancia + "\n");
    }

    public static boolean juegoTerminado() {
        return distanciaJugador >= Distancia_Meta;
    }

    //Aquí use "Arraylist" para buscar un tema aleatorio que no se haya usado para que simule que es el dado
    public static Integer getTemaRandom() {
        List<Integer> availableThemes = new ArrayList<>();
        for (int i = 0; i < questionThemes.length; i++) {
            if (!questionThemes[i].isEmpty()) {
                availableThemes.add(i + 1);
            }
        }
        if (availableThemes.isEmpty()) {
            return null;
        }
        Collections.shuffle(availableThemes);
        return availableThemes.get(0);
    }

    public static String getNombreTema(int tema) {
        switch (tema) {
            case 1: return "México";
            case 2: return "Historia Universal";
            case 3: return "Geografía Universal";
            case 4: return "Artes, Deportes y Entretenimientos";
            case 5: return "Ciencias y Tecnología";
            case 6: return "Cultura General";
            default: return "Desconocido";
        }
    }
}