package gregicality.multiblocks.api.metatileentity;

public interface IGCYMMultiBlock {
    /**
     * @return true if this multiblock has strict voltage tiering, otherwise false
     */
    boolean isTiered();

    /**
     * @return true if this multiblock runs in parallel
     */
    boolean isParallel();
}
