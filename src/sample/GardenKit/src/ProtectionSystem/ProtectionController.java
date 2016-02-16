package sample.GardenKit.src.ProtectionSystem;

import sample.GardenKit.src.Common.SwitchBoard;
import sample.GardenKit.src.HeatingSystem.Heater;
import sample.GardenKit.src.LoggingSystem.Log;
import sample.GardenKit.src.Nature.Phenomena.AnimalListener;
import sample.GardenKit.src.Nature.Phenomena.WindListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by nayan on 2/14/16.
 */
public class ProtectionController implements SwitchBoard,WindListener,AnimalListener {

    private List<MeshProtector> meshes;
    private WindProtector windProtector;
    private static ProtectionController board;


    private ProtectionController(){

        meshes = new ArrayList<MeshProtector>();
        windProtector = new WindProtector();

    }

    public static ProtectionController getInstance(){
        if(board == null){
            board = new ProtectionController();
        }
        return board;
    }

    @Override
    public void masterOFF() {

        for(MeshProtector mesh : meshes)
            mesh.switchOFF();
        String size = String.valueOf(meshes.size());
        Log log = new Log();
        log.record(size+ " Meshes disabled");
    }

    @Override
    public void masterON() {

        for(MeshProtector mesh : meshes)
            mesh.switchON();

        String size = String.valueOf(meshes.size());
        Log log = new Log();
        log.record(size+ " Meshes enabled");
    }

    @Override
    public void removeSwitch(Object mesh) {
        MeshProtector mp = (MeshProtector)mesh;
        for(MeshProtector mesh1: meshes){
            if(mesh1 == mp){
                meshes.remove(mesh1);
                break;
            }
        }
        Log log = new Log();
        log.record("Mesh removed");
    }

    @Override
    public void addSwitch(Object mesh1) {
        MeshProtector mesh = (MeshProtector)mesh1;
        meshes.add(mesh);
        Log log = new Log();
        log.record("Mesh installed");
    }


    @Override
    public void windy() {
        windProtector.switchON();
        masterON();
    }

    @Override
    public void windStopped() {
        windProtector.switchOFF();
    }

    @Override
    public void animalsEntered() {
        masterON();
    }

    @Override
    public void animalsExited() {
        masterOFF();
    }
}
