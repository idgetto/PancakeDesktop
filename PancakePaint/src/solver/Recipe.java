package solver;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class Recipe {
    private List<Phase> _phases;

    public Recipe() {
        _phases = new ArrayList<Phase>();
    }

    public void addPhase(Phase phase) {
        _phases.add(phase);
    }

    public List<Phase> getPhases() {
        return _phases;
    }
    
    public Phase getPhase(int index) {
        return _phases.get(index);
    }

    public String toString() {
        String str = "";
        for (int phaseIndex = 0; phaseIndex < _phases.size(); ++phaseIndex) {
            str += "Phase: " + phaseIndex + "\n"; 

            Phase phase = _phases.get(phaseIndex);
            List<Stroke> strokes = phase.getStrokes();

            for (int strokeIndex = 0; strokeIndex < strokes.size(); ++strokeIndex) {
                str += "Stroke " + strokeIndex + "\n"; 
                
                Stroke stroke = strokes.get(strokeIndex);
                for (Point p : stroke.getPoints()) {
                    str += "row: " + p.y + ", col: " + p.x + "\n";
                }
                str += "\n";
            }
        }
        return str;
    }
}
